package likelion.univ.post.controller;


import likelion.univ.domain.post.dto.response.PostDetailResponseDto;
import likelion.univ.domain.post.dto.response.PostCommandResponseDto;
import likelion.univ.domain.post.entity.Post;
import likelion.univ.post.dto.PostCreateRequestDto;
import likelion.univ.post.dto.PostUpdateRequestDto;
import likelion.univ.post.repository.PostReadRepository;
import likelion.univ.post.usecase.*;
import likelion.univ.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/community/posts")
public class PostController {

    private final PostCreateUseCase postCreateUseCase;
    private final PostUpdateUseCase postUpdateUsecase;
    private final PostDeleteUseCase postDeleteUseCase;
    private final PostReadRepository postReadRepository;

    /* read */
    @GetMapping("/all/author/{userId}")
    public SuccessResponse<?> findPostsByAuthor(@PathVariable Long userId, @RequestParam Integer page, @RequestParam Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<PostDetailResponseDto> response = postReadRepository.findPostsByAuthor(userId, pageRequest);
        return SuccessResponse.of(response);
    }

    @GetMapping("/all/commentAuthor/{authorId}")
    public SuccessResponse<?> findPostsByCommentAuthor(@PathVariable Long authorId, @RequestParam Integer page, @RequestParam Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<PostDetailResponseDto> response = postReadRepository.findPostsByCommentAuthor(authorId, pageRequest);
        return SuccessResponse.of(response);
    }

    /* command */
    @PostMapping("/new")
    public SuccessResponse<?> createPost(@RequestBody @Valid PostCreateRequestDto request/*, BindingResult bindingResult*/) {
        PostCommandResponseDto response = postCreateUseCase.execute(request);
        return SuccessResponse.of(response);
    }
    @PatchMapping("/{postId}")
    public SuccessResponse<?> updatePost(@PathVariable Long postId, @RequestBody PostUpdateRequestDto request) {
        PostCommandResponseDto response = postUpdateUsecase.execute(postId, request);
        return SuccessResponse.of(response);
    }

    @DeleteMapping("/{postId}")
    public SuccessResponse<?> deletePost(@PathVariable Long postId) {
        postDeleteUseCase.execute(postId);
        return SuccessResponse.empty();
    }

    /* 내부 메서드 */
    private static List<PostDetailResponseDto> getPostDetailResponseDtos(List<Post> posts) {
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
}
