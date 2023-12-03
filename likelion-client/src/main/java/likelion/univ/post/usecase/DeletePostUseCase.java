package likelion.univ.post.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.post.dto.request.DeletePostServiceDto;
import likelion.univ.domain.post.service.PostDomainService;
import likelion.univ.utils.AuthenticatedUserUtils;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class DeletePostUseCase {
    private final PostDomainService postDomainService;
    private final AuthenticatedUserUtils userUtils;

    public void execute(Long postId) {
        postDomainService.deletePost(serviceDtoBy(postId));
        return ;
    }

    private DeletePostServiceDto serviceDtoBy(Long postId) {
        return DeletePostServiceDto.builder()
                .postId(postId)
                .loginUserId(userUtils.getCurrentUserId())
                .build();
    }
}
