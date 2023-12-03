package likelion.univ.post.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.post.dto.request.GetPostByAuthorServiceDto;
import likelion.univ.domain.post.dto.response.PostSimpleResponseDto;
import likelion.univ.domain.post.service.PostDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class GetMyPostsUseCase {
    private final PostDomainService postDomainService;

    public List<PostSimpleResponseDto> execute(Long authorId, Pageable pageable) {
        GetPostByAuthorServiceDto requestDto = new GetPostByAuthorServiceDto(authorId, pageable);
        return postDomainService.getPostsByAuthorId(requestDto);
    }
}
