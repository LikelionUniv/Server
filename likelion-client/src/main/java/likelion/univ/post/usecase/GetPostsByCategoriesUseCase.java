package likelion.univ.post.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.common.response.PageResponse;
import likelion.univ.domain.post.dto.enums.PostOrderCondition;
import likelion.univ.domain.post.dto.request.GetPostsByCategoriesCommand;
import likelion.univ.domain.post.dto.response.PostData;
import likelion.univ.domain.post.dto.response.PostSimpleData;
import likelion.univ.domain.post.dto.enums.MainCategory;
import likelion.univ.domain.post.dto.enums.SubCategory;
import likelion.univ.domain.post.service.PostDomainService;
import likelion.univ.post.dto.response.PostResponseDto;
import likelion.univ.post.processor.GetOrCreatePostCountInfoProcessor;
import likelion.univ.user.dto.response.UserPagePostsDto;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class GetPostsByCategoriesUseCase {
    private final PostDomainService postDomainService;
    private final GetOrCreatePostCountInfoProcessor getOrCreatePostCountInfoProcessor;

    public PageResponse<PostResponseDto> execute(PostOrderCondition orderCondition, String mainCategory, String subCategory, Pageable pageable) {
        GetPostsByCategoriesCommand request = new GetPostsByCategoriesCommand(MainCategory.findByTitle(mainCategory), SubCategory.findByTitle(subCategory), pageable);
        if (orderCondition.equals(PostOrderCondition.LIKE_COUNT_ORDER)) {
            Page<PostSimpleData> postSimpleDataPage = postDomainService.getByCategoriesOrderByLikeCount(request);
            return PageResponse.of(postSimpleDataPage.map(p-> PostResponseDto.of(p, getOrCreatePostCountInfoProcessor.execute(p.postId()))));

        } else if (orderCondition.equals(PostOrderCondition.COMMENT_COUNT_ORDER)) {
            Page<PostSimpleData> postSimpleDataPage = postDomainService.getByCategoriesOrderByCommentCount(request);
            return PageResponse.of(postSimpleDataPage.map(p-> PostResponseDto.of(p, getOrCreatePostCountInfoProcessor.execute(p.postId()))));
        }
        Page<PostSimpleData> postSimpleDataPage = postDomainService.getByCategoriesOrderByCreatedData(request);
        return PageResponse.of(postSimpleDataPage.map(p-> PostResponseDto.of(p, getOrCreatePostCountInfoProcessor.execute(p.postId()))));
    }
}
