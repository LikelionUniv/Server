package likelion.univ.domain.post.service;

import likelion.univ.domain.post.adaptor.PostAdaptor;
import likelion.univ.domain.post.dto.request.*;
import likelion.univ.domain.post.dto.response.PostCommandResponseDto;
import likelion.univ.domain.post.dto.response.PostDetailResponseDto;
import likelion.univ.domain.post.entity.Post;
import likelion.univ.domain.post.entity.enums.MainCategory;
import likelion.univ.domain.post.entity.enums.SubCategory;
import likelion.univ.domain.post.exception.PostNoAuthorizationException;
import likelion.univ.domain.user.adaptor.UserAdaptor;
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
    private final UserAdaptor userAdaptor;

    public List<PostDetailResponseDto> getLatestPosts(GetLatestPostsServiceDto request) {
        // 페이지네이션으로 createdAt 기준으로 order
        MainCategory mainCategory = request.mainCategory();
        SubCategory subCategory = request.subCategory();
        Pageable pageable = request.pageable();
        List<PostDetailResponseDto> responses = postAdaptor.findAllByCategories(mainCategory, subCategory, pageable);
        return responses;
    }

    public List<PostDetailResponseDto> getCommentedPosts(GetUserPostsServiceDto request) {
        Long userId = request.userId();
        Pageable pageable = request.pageable();
        List<PostDetailResponseDto> responses = postAdaptor.findCommentedPosts(userId, pageable);
        return responses;
    }

    public List<PostDetailResponseDto> getLikedPosts(GetUserPostsServiceDto request) {
        Long userId = request.userId();
        Pageable pageable = request.pageable();
        List<PostDetailResponseDto> responses = postAdaptor.findLikedPosts(userId, pageable);
        return responses;
    }

    public List<PostDetailResponseDto> getPostsByAuthorId(GetPostByAuthorServiceDto request) {
        Long authorId = request.authorId();
        Pageable pageable = request.pageable();
        List<PostDetailResponseDto> responses = postAdaptor.findPostsByAuthorId(authorId, pageable);
        return responses;
    }

    public PostCommandResponseDto createPost(PostCreateServiceDto request) {
        Post post = createEntity(request);
        Long savedId = postAdaptor.save(post);
        return PostCommandResponseDto.builder()
                .postId(savedId)
                .build();
    }

    public PostCommandResponseDto editPost(PostUpdateServiceDto request) {
        Post post = postAdaptor.findById(request.getPostId());
        if (!(post.getAuthor().getId().equals(request.getLoginUserId()))) {
            throw new PostNoAuthorizationException();
        }
        post.edit(request);
        Long savedId = postAdaptor.save(post);
        return PostCommandResponseDto.builder()
                .postId(savedId)
                .build();
    }
    public void deletePost(PostDeleteServiceDto request) {
        Post post = postAdaptor.findById(request.getPostId());
        if (!(post.getAuthor().getId().equals(request.getLoginUserId()))) {
            throw new PostNoAuthorizationException();
        }
        postAdaptor.delete(post);
    }

    private Post createEntity(PostCreateServiceDto request) {
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
