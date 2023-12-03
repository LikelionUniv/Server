package likelion.univ.domain.post.service;

import likelion.univ.domain.comment.adaptor.CommentAdaptor;
import likelion.univ.domain.comment.dto.CommentDetailResponseDto;
import likelion.univ.domain.comment.dto.ParentCommentDetailResponseDto;
import likelion.univ.domain.comment.entity.Comment;
import likelion.univ.domain.follow.adaptor.FollowAdaptor;
import likelion.univ.domain.like.postlike.adaptor.PostLikeAdaptor;
import likelion.univ.domain.post.adaptor.PostAdaptor;
import likelion.univ.domain.post.dto.request.*;
import likelion.univ.domain.post.dto.response.PostIdResponseDto;
import likelion.univ.domain.post.dto.response.PostDetailResponseDto;
import likelion.univ.domain.post.dto.response.PostSimpleResponseDto;
import likelion.univ.domain.post.entity.Post;
import likelion.univ.domain.post.entity.enums.MainCategory;
import likelion.univ.domain.post.entity.enums.SubCategory;
import likelion.univ.domain.post.exception.PostNoAuthorizationException;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import likelion.univ.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostDomainService {

    private final PostAdaptor postAdaptor;
    private final CommentAdaptor commentAdaptor;
    private final UserAdaptor userAdaptor;
    private final FollowAdaptor followAdaptor;
    private final PostLikeAdaptor postLikeAdaptor;

    public PostDetailResponseDto getPostDetail(GetPostDetailServiceDto serviceDto) {
        Long postId = serviceDto.postId();
        Long loginUserId = serviceDto.loginUserId();
        User loginUser = userAdaptor.findById(loginUserId);
        /* 문제의 구간 */
        List<CommentDetailResponseDto> comments = commentAdaptor.findCommentsByPostId(postId, loginUserId);

        Post post = postAdaptor.findById(postId);
        User author = post.getAuthor();
        Boolean hasFollowedUser = followAdaptor.hasFollowedUser(loginUserId, author.getId());
        Boolean isLikedPost = postLikeAdaptor.existsByPostIdAndAuthorId(postId, loginUserId);


        return PostDetailResponseDto.builder()
                .postId(post.getId())
                .authorId(author.getId())
                .authorName(author.getProfile().getName())
                .authorProfileImageUrl(author.getProfile().getProfileImage())
                .universityName(author.getUniversityInfo().getUniversity().getName())
                .isMyPost(author.getId().equals(loginUserId))
                .hasFollowedAuthor(hasFollowedUser)
                .isLikedPost(isLikedPost)
                .title(post.getTitle())
                .body(post.getBody())
                .comments(comments)
                .build();
    }

    public List<PostSimpleResponseDto> getLatestPosts(GetLatestPostsServiceDto request) {
        // 페이지네이션으로 createdAt 기준으로 order
        MainCategory mainCategory = request.mainCategory();
        SubCategory subCategory = request.subCategory();
        Pageable pageable = request.pageable();
        List<PostSimpleResponseDto> responses = postAdaptor.findAllByCategories(mainCategory, subCategory, pageable);
        return responses;
    }

    public List<PostSimpleResponseDto> getCommentedPosts(GetUserPostsServiceDto request) {
        Long userId = request.userId();
        Pageable pageable = request.pageable();
        List<PostSimpleResponseDto> responses = postAdaptor.findCommentedPosts(userId, pageable);
        return responses;
    }

    public List<PostSimpleResponseDto> getLikedPosts(GetUserPostsServiceDto request) {
        Long userId = request.userId();
        Pageable pageable = request.pageable();
        List<PostSimpleResponseDto> responses = postAdaptor.findLikedPosts(userId, pageable);
        return responses;
    }

    public List<PostSimpleResponseDto> getPostsByAuthorId(GetPostByAuthorServiceDto request) {
        Long authorId = request.authorId();
        Pageable pageable = request.pageable();
        List<PostSimpleResponseDto> responses = postAdaptor.findPostsByAuthorId(authorId, pageable);
        return responses;
    }

    public PostIdResponseDto createPost(CreatePostServiceDto request) {
        Post post = createEntity(request);
        Long savedId = postAdaptor.save(post);
        return PostIdResponseDto.builder()
                .postId(savedId)
                .build();
    }

    public PostIdResponseDto editPost(UpdatePostServiceDto request) {
        Post post = postAdaptor.findById(request.getPostId());
        if (!(post.getAuthor().getId().equals(request.getLoginUserId()))) {
            throw new PostNoAuthorizationException();
        }
        post.edit(request);
        Long savedId = postAdaptor.save(post);
        return PostIdResponseDto.builder()
                .postId(savedId)
                .build();
    }
    public void deletePost(DeletePostServiceDto request) {
        Post post = postAdaptor.findById(request.getPostId());
        if (!(post.getAuthor().getId().equals(request.getLoginUserId()))) {
            throw new PostNoAuthorizationException();
        }
        postAdaptor.delete(post);
    }

    private Post createEntity(CreatePostServiceDto request) {
        return  Post.builder()
                .author(userAdaptor.findById(request.getAuthorId()))
                .title(request.getTitle())
                .body(request.getBody())
                .thumbnail(request.getThumbnail())
                .mainCategory(MainCategory.valueOf(request.getMainCategory()))
                .subCategory(SubCategory.valueOf(request.getSubCategory()))
                .build();
    }

}
