package likelion.univ.adminPost.usecase;

import likelion.univ.adminPost.dto.response.PostInfoResponseDto;
import likelion.univ.annotation.UseCase;
import likelion.univ.domain.post.service.PostDomainService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class DeleteSelectedPostsUseCase {

    private final PostDomainService postDomainService;

    private final PostInfoResponseDto postInfoResponseDto;

    public void execute(List<Long> selectedIds){
        for(Long selectedId : selectedIds) {
            postInfoResponseDto.deletePostByAdmin(selectedId);
        }
    }
}
