package likelion.univ.domain.post.service;

import likelion.univ.domain.post.adaptor.PostAdaptor;
import likelion.univ.domain.post.dto.*;
import likelion.univ.domain.post.entity.Post;
import likelion.univ.domain.post.entity.enums.MainCategory;
import likelion.univ.domain.post.entity.enums.SubCategory;
import likelion.univ.domain.post.exception.PostNoAuthorizationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService{

    private final PostAdaptor postAdaptor;


    public PostSimpleResponseDto createPost(PostCreateServiceDto request) {
        Post post = createEntity(request);
        Long savedId = postAdaptor.save(post);
        return PostSimpleResponseDto.builder()
                .postId(savedId)
                .build();
    }

    public List<PostDetailResponseDto> findPosts(Integer page, Integer limit) {
        List<Post> posts= postAdaptor.findPosts(page,limit);
        return posts.stream()
                .map(post -> PostDetailResponseDto.builder()
                        .id(post.getId())
                        .authorId(post.getAuthor().getId())
                        .author(post.getAuthor().getProfile().getName())
                        .title(post.getTitle())
                        .body(post.getBody())
                        .thumbnail(post.getThumbnail())
                        .mainCategory(post.getMainCategory())
                        .subCategory(post.getSubCategory())
                        .build())
                .toList();
    }

    public PostSimpleResponseDto editPost(PostUpdateServiceDto request) {
        Post post = postAdaptor.findById(request.getPostId());
        if (!(post.getAuthor().getId().equals(request.getUserId()))) {
            throw new PostNoAuthorizationException();
        }
        post.edit(request);
        Long savedId = postAdaptor.save(post);
        return PostSimpleResponseDto.builder()
                .postId(savedId)
                .build();
    }
    public void deletePost(PostDeleteServiceDto request) {
        Post post = postAdaptor.findById(request.getPostId());
        if (!(post.getAuthor().getId().equals(request.getUserId()))) {
            throw new PostNoAuthorizationException();
        }
        postAdaptor.delete(post);
        return;
    }

    private static Post createEntity(PostCreateServiceDto request) {
        return  Post.builder()
                .author(request.getUser())
                .title(request.getTitle())
                .body(request.getBody())
                .thumbnail(request.getThumbnail())
                .mainCategory(MainCategory.valueOf(request.getMainCategory()))
                .subCategory(SubCategory.valueOf(request.getSubCategory()))
                .build();
    }



}
