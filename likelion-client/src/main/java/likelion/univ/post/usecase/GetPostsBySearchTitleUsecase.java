package likelion.univ.post.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.common.response.PageResponse;
import likelion.univ.domain.post.dto.enums.PostOrderCondition;
import likelion.univ.domain.post.dto.request.GetPostsByCategorySearchCommand;
import likelion.univ.domain.post.dto.request.GetPostsBySearchTitleCommand;
import likelion.univ.domain.post.dto.response.PostSimpleData;
import likelion.univ.domain.post.service.PostDomainService;
import likelion.univ.post.dto.response.PostResponseDto;
import likelion.univ.post.processor.GetOrCreatePostCountInfoProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@UseCase
@RequiredArgsConstructor
public class GetPostsBySearchTitleUsecase {

    private final PostDomainService postDomainService;
    private final GetOrCreatePostCountInfoProcessor getOrCreatePostCountInfoProcessor;

    public PageResponse<PostResponseDto> execute(PostOrderCondition orderCondition, String searchTitle,
                                                 String mainCategory, String subCategory, Pageable pageable) {
        if (isSearchWithCategories(mainCategory)) {
            Page<PostSimpleData> postSimpleDataPage = getByCategoriesAndSearchTitle(orderCondition, searchTitle,
                    mainCategory, subCategory, pageable);
            return PageResponse.of(postSimpleDataPage.map(
                    p -> PostResponseDto.of(p, getOrCreatePostCountInfoProcessor.execute(p.postId()))));
        }
        Page<PostSimpleData> postSimpleDataPage = getBySearchTitle(orderCondition, searchTitle, pageable);
        return PageResponse.of(postSimpleDataPage.map(
                p -> PostResponseDto.of(p, getOrCreatePostCountInfoProcessor.execute(p.postId()))));
    }

    /* ----- 내부메서드 ----- */
    private static boolean isSearchWithCategories(String mainCategory) {
        return !mainCategory.equals("전체 게시판");
    }

    private Page<PostSimpleData> getByCategoriesAndSearchTitle(PostOrderCondition orderCondition, String searchTitle,
                                                               String mainCategory, String subCategory,
                                                               Pageable pageable) {
        GetPostsByCategorySearchCommand request = new GetPostsByCategorySearchCommand(orderCondition, searchTitle,
                mainCategory, subCategory);
        Page<PostSimpleData> postSimpleDataPage = postDomainService.getByCategoriesAndSearchTitle(request, pageable);
        return postSimpleDataPage;
    }

    private Page<PostSimpleData> getBySearchTitle(PostOrderCondition orderCondition, String searchTitle,
                                                  Pageable pageable) {
        GetPostsBySearchTitleCommand request = new GetPostsBySearchTitleCommand(orderCondition, searchTitle);
        Page<PostSimpleData> postSimpleDataPage = postDomainService.getBySearchTitle(request, pageable);
        return postSimpleDataPage;
    }
}
