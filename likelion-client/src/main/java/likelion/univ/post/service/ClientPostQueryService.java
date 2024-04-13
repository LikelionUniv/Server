package likelion.univ.post.service;

import static likelion.univ.domain.post.dto.enums.PostOrderCondition.COMMENT_COUNT_ORDER;
import static likelion.univ.domain.post.dto.enums.PostOrderCondition.LIKE_COUNT_ORDER;

import likelion.univ.common.response.PageResponse;
import likelion.univ.domain.post.dto.enums.MainCategory;
import likelion.univ.domain.post.dto.enums.PostOrderCondition;
import likelion.univ.domain.post.dto.enums.SubCategory;
import likelion.univ.domain.post.dto.request.GetPostDetailCommand;
import likelion.univ.domain.post.dto.request.GetPostsByCategoriesCommand;
import likelion.univ.domain.post.dto.request.GetPostsByCategorySearchCommand;
import likelion.univ.domain.post.dto.request.GetPostsBySearchTitleCommand;
import likelion.univ.domain.post.dto.response.PostDetailData;
import likelion.univ.domain.post.dto.response.PostEditData;
import likelion.univ.domain.post.dto.response.PostSimpleData;
import likelion.univ.domain.post.service.PostDomainService;
import likelion.univ.post.dto.response.PostDetailResponseDto;
import likelion.univ.post.dto.response.PostEditResponseDto;
import likelion.univ.post.dto.response.PostResponseDto;
import likelion.univ.post.processor.GetOrCreatePostCountInfoProcessor;
import likelion.univ.utils.AuthenticatedUserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ClientPostQueryService {

    private final PostDomainService postDomainService;
    private final AuthenticatedUserUtils authenticatedUserUtils;
    private final GetOrCreatePostCountInfoProcessor getOrCreatePostCountInfoProcessor;

    public PostDetailResponseDto getDetail(Long postId) {
        Long loginUserId = authenticatedUserUtils.getCurrentUserId();
        GetPostDetailCommand serviceRequestDto = new GetPostDetailCommand(postId, loginUserId);
        PostDetailData serviceResponseDto = postDomainService.getPostDetail(serviceRequestDto);
        return PostDetailResponseDto.of(
                serviceResponseDto,
                getOrCreatePostCountInfoProcessor.execute(serviceResponseDto.postId()),
                loginUserId
        );
    }

    public PostEditResponseDto getPostEdit(Long postId) {
        PostEditData postEdit = postDomainService.getPostEditById(postId);
        return PostEditResponseDto.of(postEdit);
    }

    public PageResponse<PostResponseDto> getPostsByCategories(
            PostOrderCondition orderCondition,
            String mainCategory,
            String subCategory,
            Pageable pageable
    ) {
        GetPostsByCategoriesCommand request = new GetPostsByCategoriesCommand(
                MainCategory.findByTitle(mainCategory),
                SubCategory.findByTitle(subCategory)
        );
        if (orderCondition.equals(LIKE_COUNT_ORDER)) {
            Page<PostSimpleData> postSimpleDataPage = postDomainService.getByCategoriesOrderByLikeCount(
                    request, pageable
            );
            return PageResponse.of(postSimpleDataPage.map(
                    p -> PostResponseDto.of(p, getOrCreatePostCountInfoProcessor.execute(p.postId()))
            ));

        } else if (orderCondition.equals(COMMENT_COUNT_ORDER)) {
            Page<PostSimpleData> postSimpleDataPage = postDomainService.getByCategoriesOrderByCommentCount(request,
                    pageable);
            return PageResponse.of(postSimpleDataPage.map(
                    p -> PostResponseDto.of(p, getOrCreatePostCountInfoProcessor.execute(p.postId()))
            ));
        }
        Page<PostSimpleData> postSimpleDataPage = postDomainService.getByCategoriesOrderByCreatedData(request,
                pageable);
        return PageResponse.of(postSimpleDataPage.map(
                p -> PostResponseDto.of(p, getOrCreatePostCountInfoProcessor.execute(p.postId()))
        ));
    }

    public PageResponse<PostResponseDto> getPostBySearchTitle(
            PostOrderCondition orderCondition,
            String searchTitle,
            String mainCategory,
            String subCategory,
            Pageable pageable
    ) {
        if (!mainCategory.equals("전체 게시판")) {
            var request = new GetPostsByCategorySearchCommand(
                    orderCondition,
                    searchTitle,
                    mainCategory,
                    subCategory
            );
            var result = postDomainService.getByCategoriesAndSearchTitle(request, pageable);
            return PageResponse.of(result.map(p ->
                    PostResponseDto.of(
                            p,
                            getOrCreatePostCountInfoProcessor.execute(p.postId())
                    )));
        }
        var request = new GetPostsBySearchTitleCommand(orderCondition, searchTitle);
        var result = postDomainService.getBySearchTitle(request, pageable);
        return PageResponse.of(result.map(p ->
                PostResponseDto.of(
                        p,
                        getOrCreatePostCountInfoProcessor.execute(p.postId())
                )));
    }
}
