package likelion.univ.adminPost.usecase;

import likelion.univ.adminPost.dto.response.PostInfoResponseDto;
import likelion.univ.annotation.UseCase;
import likelion.univ.domain.post.adaptor.PostAdaptor;
import likelion.univ.domain.post.service.PostDomainService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class DeleteSelectedPostsUseCase {

    private final PostAdaptor postAdaptor;
    public void execute(List<Long> selectedIds){
        postAdaptor.deleteAllByIdInBatch(selectedIds);
    }
}
