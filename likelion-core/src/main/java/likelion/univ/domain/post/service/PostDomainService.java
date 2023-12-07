package likelion.univ.domain.post.service;

import likelion.univ.domain.comment.adaptor.CommentAdaptor;
import likelion.univ.domain.comment.dto.response.ChildCommentData;
import likelion.univ.domain.comment.dto.response.ParentCommentData;
import likelion.univ.domain.follow.adaptor.FollowAdaptor;
import likelion.univ.domain.like.postlike.adaptor.PostLikeAdaptor;
import likelion.univ.domain.post.adaptor.PostAdaptor;
import likelion.univ.domain.post.dto.request.*;
import likelion.univ.domain.post.dto.response.PostIdData;
import likelion.univ.domain.post.dto.response.PostDetailData;
import likelion.univ.domain.post.dto.response.PostSimpleData;
import likelion.univ.domain.post.entity.Post;
import likelion.univ.domain.post.entity.enums.MainCategory;
import likelion.univ.domain.post.entity.enums.SubCategory;
import likelion.univ.domain.post.exception.PostNoAuthorizationException;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import likelion.univ.domain.user.entity.Profile;
import likelion.univ.domain.user.entity.UniversityInfo;
import likelion.univ.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
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

    public PostDetailData getPostDetail(GetPostDetailCommand serviceDto) {
        Long postId = serviceDto.postId();
        Long loginUserId = serviceDto.loginUserId();

        // post entity data
        Post post = postAdaptor.findById(postId);
        Boolean isLikedPost = postLikeAdaptor.existsByPostIdAndAuthorId(postId, loginUserId);
        Integer postLikeCount = Math.toIntExact(postLikeAdaptor.countByPostId(postId));

        // comment entity data
        List<ParentCommentData> parentComments = commentAdaptor.findParentCommentsByPostId(postId);
        List<ChildCommentData> childComments = commentAdaptor.findChildCommentsByPostId(postId);


        // user entity data
        User author = post.getAuthor();
        Profile authorProfile = author.getProfile();
        UniversityInfo authorUniversityInfo = author.getUniversityInfo();
        String authorProfileImageUrl = authorProfile.getProfileImage();
        Boolean hasFollowedAuthor = followAdaptor.hasFollowedUser(loginUserId, author.getId());


        return PostDetailData.builder()
                .postId(post.getId())
                .authorId(author.getId())
                .authorName(authorProfile.getName())
                .authorProfileImageUrl(authorProfileImageUrl)
                .authorOrdinal(authorUniversityInfo.getOrdinal())
                .universityName(authorUniversityInfo.getUniversity().getName())
                .hasFollowedAuthor(hasFollowedAuthor)
                .isLikedPost(isLikedPost)
                .likeCount(postLikeCount)
                .title(post.getTitle())
                .body(post.getBody())
                .parentComments(parentComments)
                .childComments(childComments)
                .build();
    }

    public List<PostSimpleData> getCategorizedPosts(GetPostsByCategoriesCommand request) {
        // 페이지네이션으로 createdAt 기준으로 order
        MainCategory mainCategory = request.mainCategory();
        SubCategory subCategory = request.subCategory();
        Pageable pageable = request.pageable();
        List<PostSimpleData> responses = postAdaptor.findAllByCategories(mainCategory, subCategory, pageable);
        return responses;
    }

    @Transactional
    public PostIdData createPost(CreatePostCommand request) {
        Post post = createEntity(request);
        Long savedId = postAdaptor.save(post);
        return PostIdData.builder()
                .postId(savedId)
                .build();
    }

    @Transactional
    public PostIdData editPost(UpdatePostCommand request) {
        Post post = postAdaptor.findById(request.postId());
        if (!(post.getAuthor().getId().equals(request.loginUserId()))) {
            throw new PostNoAuthorizationException();
        }
        post.edit(request);
        Long savedId = postAdaptor.save(post);
        return PostIdData.builder()
                .postId(savedId)
                .build();
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
                .mainCategory(MainCategory.valueOf(request.mainCategory()))
                .subCategory(SubCategory.valueOf(request.subCategory()))
                .build();
    }

}
