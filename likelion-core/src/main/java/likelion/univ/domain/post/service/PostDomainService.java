package likelion.univ.domain.post.service;

import likelion.univ.domain.post.adaptor.PostAdaptor;
import likelion.univ.domain.post.dto.*;
import likelion.univ.domain.post.entity.Post;
import likelion.univ.domain.post.exception.PostNoAuthorizationException;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostDomainService {

    private final PostAdaptor postAdaptor;
    private final UserAdaptor userAdaptor;


    public PostCommandResponseDto createPost(PostCreateServiceDto request) {
        Post post = createEntity(request);
        Long savedId = postAdaptor.save(post);
        return PostCommandResponseDto.builder()
                .postId(savedId)
                .build();
    }

    public List<PostDetailResponseDto> findPosts(Integer page, Integer limit) {
        List<Post> posts= postAdaptor.findPosts(page,limit);
        return posts.stream()
                .map(post -> PostDetailResponseDto.builder()
                        .id(post.getId())
                        .authorId(post.getAuthor().getId())
                        .authorName(post.getAuthor().getProfile().getName())
                        .title(post.getTitle())
                        .body(post.getBody())
                        .thumbnail(post.getThumbnail())
                        .mainCategory(post.getMainCategory())
                        .subCategory(post.getSubCategory())
                        .build())
                .toList();
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
        return;
    }

    private Post createEntity(PostCreateServiceDto request) {
        return  Post.builder()
                .author(userAdaptor.findById(request.getAuthorId()))
                .title(request.getTitle())
                .body(request.getBody())
                .thumbnail(request.getThumbnail())
                // TODO : category 입력되도록 수정
//                .mainCategory(MainCategory.valueOf(request.getMainCategory()))
//                .subCategory(SubCategory.valueOf(request.getSubCategory()))
                .build();
    }



}
