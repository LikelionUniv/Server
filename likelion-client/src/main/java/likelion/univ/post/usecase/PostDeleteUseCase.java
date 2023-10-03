package likelion.univ.post.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.post.dto.PostDeleteServiceDto;
import likelion.univ.domain.post.service.PostDomainService;
import likelion.univ.utils.AuthentiatedUserUtils;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class PostDeleteUseCase {
    private final PostDomainService postDomainService;
    private final AuthentiatedUserUtils userUtils;

    public void execute(Long postId) {
        postDomainService.deletePost(serviceDtoBy(postId));
        return ;
    }

    private PostDeleteServiceDto serviceDtoBy(Long postId) {
        return PostDeleteServiceDto.builder()
                .postId(postId)
                .loginUserId(userUtils.getCurrentUserId())
                .build();
    }
}
