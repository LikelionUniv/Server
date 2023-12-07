package likelion.univ.post.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.post.dto.request.GetPostsByCategoriesCommand;
import likelion.univ.domain.post.dto.response.PostSimpleData;
import likelion.univ.domain.post.entity.enums.MainCategory;
import likelion.univ.domain.post.entity.enums.SubCategory;
import likelion.univ.domain.post.service.PostDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class GetPostsByCategoriesUseCase {
    private final PostDomainService postDomainService;

    public List<PostSimpleData> execute(MainCategory mainCategory, SubCategory subCategory, Pageable pageable) {
        GetPostsByCategoriesCommand request = new GetPostsByCategoriesCommand(mainCategory, subCategory, pageable);
        return postDomainService.getCategorizedPosts(request);
    }
}
