package likelion.univ.adminPost.controller;

import static org.springframework.data.domain.Sort.Direction.DESC;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import likelion.univ.adminPost.dto.response.PostInfoResponseDto;
import likelion.univ.adminPost.usecase.DeleteSelectedPostsUseCase;
import likelion.univ.adminPost.usecase.GetPostsByCategoriesAndUniversityUseCase;
import likelion.univ.common.response.PageResponse;
import likelion.univ.domain.post.dto.enums.MainCategory;
import likelion.univ.domain.post.dto.enums.SubCategory;
import likelion.univ.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController("adminPost.controller.RepresentativeController")
@RequestMapping("/v1/univAdmin")
@RequiredArgsConstructor
@Tag(name = "PostByUnivAdmin", description = "학교 대표에 의한 게시글 관련 API")
public class RepresentativeController {

    private final DeleteSelectedPostsUseCase deleteSelectedPostsUseCase;
    private final GetPostsByCategoriesAndUniversityUseCase getPostsByCategoriesAndUniversityUseCase;

    @Operation(summary = "카테고리 별 게시글 조회")
    @GetMapping("/posts")
    public SuccessResponse<PageResponse<PostInfoResponseDto>> getProjectsOfFreeBoardPosts(
            @ParameterObject
            @PageableDefault(size = 6, page = 0, sort = "created_date", direction = DESC)
            Pageable pageable,
            @RequestParam("mainCategory") MainCategory mainCategory,
            @RequestParam("subCategory") SubCategory subCategory
    ) {
        PageResponse<PostInfoResponseDto> response =
                getPostsByCategoriesAndUniversityUseCase.execute(pageable, mainCategory, subCategory);
        return SuccessResponse.of(response);
    }

    @Operation(summary = "선택된 게시글들 삭제")
    @DeleteMapping("/posts")
    public SuccessResponse<Object> getProjectsOfFreeBoardPosts(
            @RequestParam("selectedIds") List<Long> selectedIds
    ) {
        deleteSelectedPostsUseCase.execute(selectedIds);
        return SuccessResponse.empty();
    }
}

