package likelion.univ.post.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.post.dto.request.GetUserPostsServiceDto;
import likelion.univ.domain.post.dto.response.PostSimpleResponseDto;
import likelion.univ.domain.post.service.PostDomainService;
import likelion.univ.utils.AuthenticatedUserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;
@UseCase
@RequiredArgsConstructor
public class GetLikedPostsUseCase {
    private final PostDomainService postDomainService;
    private final AuthenticatedUserUtils authenticatedUserUtils;

    public List<PostSimpleResponseDto> execute(Pageable pageable) {
        Long currentUserId = authenticatedUserUtils.getCurrentUserId();
        GetUserPostsServiceDto requestDto = new GetUserPostsServiceDto(currentUserId, pageable);
        return postDomainService.getLikedPosts(requestDto);
    }
}
