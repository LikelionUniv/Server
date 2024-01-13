package likelion.univ.domain.post.service;

import likelion.univ.domain.comment.adaptor.CommentAdaptor;
import likelion.univ.domain.follow.adaptor.FollowAdaptor;
import likelion.univ.domain.like.postlike.adaptor.PostLikeAdaptor;
import likelion.univ.domain.post.adaptor.PostAdaptor;
import likelion.univ.domain.post.dto.enums.PostOrderCondition;
import likelion.univ.domain.post.dto.request.*;
import likelion.univ.domain.post.dto.response.*;
import likelion.univ.domain.post.entity.Post;
import likelion.univ.domain.post.dto.enums.MainCategory;
import likelion.univ.domain.post.dto.enums.SubCategory;
import likelion.univ.domain.post.exception.PostNoAuthorizationException;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import likelion.univ.domain.user.entity.Profile;
import likelion.univ.domain.user.entity.UniversityInfo;
import likelion.univ.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostDomainService {

    private final PostAdaptor postAdaptor;
    private final CommentAdaptor commentAdaptor;
    private final UserAdaptor userAdaptor;
    private final FollowAdaptor followAdaptor;
    private final PostLikeAdaptor postLikeAdaptor;


    public PostDetailData getPostDetail(GetPostDetailCommand request) {
        Long postId = request.postId();
        Long loginUserId = request.loginUserId();

        // post entity data
        Post post = postAdaptor.findById(postId);
        Boolean isLikedPost = postLikeAdaptor.existsByPostIdAndAuthorId(postId, loginUserId);
        Integer postLikeCount = Math.toIntExact(postLikeAdaptor.countByPostId(postId));

        // user entity data
        User author = post.getAuthor();
        Profile authorProfile = author.getProfile();
        UniversityInfo authorUniversityInfo = author.getUniversityInfo();
        String authorProfileImageUrl = authorProfile.getProfileImage();
        Boolean hasFollowedAuthor = followAdaptor.hasFollowedUser(loginUserId, author.getId());

        return PostDetailData.builder()
                .postId(post.getId())
                .mainCategory(post.getMainCategory())
                .subCategory(post.getSubCategory())
                .authorId(author.getId())
                .authorName(authorProfile.getName())
                .authorProfileImageUrl(authorProfileImageUrl)
                .authorOrdinal(authorUniversityInfo.getOrdinal())
                .universityName(authorUniversityInfo.getUniversity().getName())
                .isFollowedAuthor(hasFollowedAuthor)
                .isLikedPost(isLikedPost)
                .likeCount(postLikeCount)
                .title(post.getTitle())
                .body(post.getBody())
                .createdDate(post.getCreatedDate())
                .build();
    }

    public Page<PostSimpleData> getByCategoriesOrderByCreatedData(GetPostsByCategoriesCommand request) {
        MainCategory mainCategory = request.mainCategory();
        SubCategory subCategory = request.subCategory();
        Pageable pageable = request.pageable();
        Page<Post> posts = postAdaptor.findByCategoriesOrderByCreatedDate(mainCategory, subCategory, pageable);
        List<PostSimpleData> postSimpleDataList = posts.stream().map(PostSimpleData::of).toList();
        return new PageImpl<>(postSimpleDataList, pageable, posts.getTotalPages());
    }

    public Page<PostSimpleData> getByCategoriesOrderByLikeCount(GetPostsByCategoriesCommand request) {
        MainCategory mainCategory = request.mainCategory();
        SubCategory subCategory = request.subCategory();
        Pageable pageable = request.pageable();
        Page<Post> posts = postAdaptor.findByCategoriesOrderByLikeCount(mainCategory, subCategory, pageable);
        List<PostSimpleData> postSimpleDataList = posts.stream().map(PostSimpleData::of).toList();
        return new PageImpl<>(postSimpleDataList, pageable, posts.getTotalPages());
    }

    public Page<PostSimpleData> getByCategoriesOrderByCommentCount(GetPostsByCategoriesCommand request) {
        MainCategory mainCategory = request.mainCategory();
        SubCategory subCategory = request.subCategory();
        Pageable pageable = request.pageable();
        Page<Post> posts = postAdaptor.findByCategoriesOrderByCommentCount(mainCategory, subCategory, pageable);
        List<PostSimpleData> postSimpleDataList = posts.stream().map(PostSimpleData::of).toList();
        return new PageImpl<>(postSimpleDataList, pageable, posts.getTotalPages());
    }
    public Page<PostSimpleData> getByCategoriesAndSearchTitle(GetPostsByCategorySearchCommand request) {
        PostOrderCondition orderCondition = request.orderCondition();
        String searchTitle = request.searchTitle();
        MainCategory mainCategory = request.mainCategory();
        SubCategory subCategory = request.subCategory();
        Pageable pageable = request.pageable();

        if (orderCondition.equals(PostOrderCondition.COMMENT_COUNT_ORDER)) {
            return postAdaptor.findByCategoriesAndSearchTitleOrderByCommentCount(searchTitle, mainCategory, subCategory, pageable);
        } else if (orderCondition.equals(PostOrderCondition.LIKE_COUNT_ORDER)) {
            return postAdaptor.findByCategoriesAndSearchTitleOrderByLikeCount(searchTitle, mainCategory, subCategory, pageable);
        } // order by created date
        return postAdaptor.findByCategoriesAndSearchTitleOrderByCreatedDate(searchTitle, mainCategory, subCategory, pageable);
    }

    public Page<PostSimpleData> getBySearchTitle(GetPostsBySearchTitleCommand request) {
        PostOrderCondition orderCondition = request.orderCondition();
        String searchTitle = request.searchTitle();
        Pageable pageable = request.pageable();

        if (orderCondition.equals(PostOrderCondition.LIKE_COUNT_ORDER)) {
            return postAdaptor.findBySearchTitleOrderByLikeCount(searchTitle, pageable);
        } else if (orderCondition.equals(PostOrderCondition.COMMENT_COUNT_ORDER)) {
            return postAdaptor.findBySearchTitleOrderByCommentCount(searchTitle, pageable);
        }
        return postAdaptor.findBySearchTitleOrderByCreatedDate(searchTitle, pageable);
    }

    @Transactional
    public Long createPost(CreatePostCommand request) {
        Post post = createEntity(request);
        Long savedId = postAdaptor.save(post);
        return savedId;
    }

    @Transactional
    public Long editPost(UpdatePostCommand request) {
        Post post = postAdaptor.findById(request.postId());
        if (!(post.getAuthor().getId().equals(request.loginUserId()))) {
            throw new PostNoAuthorizationException();
        }
        post.edit(request);
        Long saveId = postAdaptor.save(post);
        return saveId;
    }
    @Transactional
    public void deletePost(DeletePostCommand request) {
        Post post = postAdaptor.findById(request.postId());
        if (!(post.getAuthor().getId().equals(request.loginUserId()))) {
            throw new PostNoAuthorizationException();
        }
        postAdaptor.delete(post);
    }

    private Post createEntity(CreatePostCommand request) {
        return  Post.builder()
                .author(userAdaptor.findById(request.authorId()))
                .title(request.title())
                .body(request.body())
                .thumbnail(request.thumbnail())
                .mainCategory(request.mainCategory())
                .subCategory(request.subCategory())
                .build();
    }

    private Page<PostData> addCountsData(Page<PostSimpleData> posts, Pageable pageable) {
        List<PostData> response = posts.stream().map(p -> {
            Long commentCount = commentAdaptor.countByPostId(p.postId());
            Long likeCount = postLikeAdaptor.countByPostId(p.postId());
            return PostData.getInstance(p, likeCount, commentCount);
        }).toList();
        return new PageImpl<>(response, pageable, posts.getTotalPages());
    }


}
