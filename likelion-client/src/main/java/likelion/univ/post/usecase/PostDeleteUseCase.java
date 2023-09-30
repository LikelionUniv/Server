package likelion.univ.post.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.post.dto.PostDeleteServiceDto;
import likelion.univ.domain.post.service.PostService;
import likelion.univ.utils.AuthentiatedUserUtils;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class PostDeleteUseCase {
    private final PostService postService;
    private final AuthentiatedUserUtils userUtils;

    public void execute(Long postId) {
        postService.deletePost(serviceDtoBy(postId));
        return ;
    }

    private PostDeleteServiceDto serviceDtoBy(Long postId) {
        return PostDeleteServiceDto.builder()
                .postId(postId)
                .loginUserId(userUtils.getCurrentUserId())
                .build();
    }
}
