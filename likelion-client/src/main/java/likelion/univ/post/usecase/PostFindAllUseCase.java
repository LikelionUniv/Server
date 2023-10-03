package likelion.univ.post.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.post.dto.PostDetailResponseDto;
import likelion.univ.domain.post.service.PostDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class PostFindAllUseCase {

    @Autowired
    private PostDomainService postDomainService;

    public List<PostDetailResponseDto> execute(Integer page, Integer limit) {
        List<PostDetailResponseDto> response = postDomainService.findPosts(page,limit);
        return response;
    }
}
