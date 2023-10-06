package likelion.univ.post.controller;


import likelion.univ.domain.post.dto.response.PostDetailResponseDto;
import likelion.univ.domain.post.dto.response.PostCommandResponseDto;
import likelion.univ.domain.post.entity.enums.MainCategory;
import likelion.univ.domain.post.entity.enums.SubCategory;
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
    @GetMapping("")
    public SuccessResponse<?> findCategorizedPosts(@RequestParam MainCategory mainCategory, @RequestParam SubCategory subCategory, @RequestParam Integer page, @RequestParam Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<PostDetailResponseDto> response = postReadRepository.findAll(mainCategory, subCategory, pageRequest);
        return SuccessResponse.of(response);
    }
    @GetMapping("/author/{userId}")
    public SuccessResponse<?> findAuthorPosts(@PathVariable Long userId, @RequestParam Integer page, @RequestParam Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<PostDetailResponseDto> response = postReadRepository.findAuthorPosts(userId, pageRequest);
        return SuccessResponse.of(response);
    }

    @GetMapping("/commentAuthor/{authorId}")
    public SuccessResponse<?> findCommentedPosts(@PathVariable Long authorId, @RequestParam Integer page, @RequestParam Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<PostDetailResponseDto> response = postReadRepository.findCommentedPosts(authorId, pageRequest);
        return SuccessResponse.of(response);
    }

    @GetMapping("/likeduser/{userId}")
    public SuccessResponse<?> findLikedPosts(@PathVariable Long userId, @RequestParam Integer page, @RequestParam Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<PostDetailResponseDto> response = postReadRepository.findLikedPosts(pageRequest);
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

}
