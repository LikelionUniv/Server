package likelion.univ.adminPost.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import likelion.univ.adminPost.dto.response.PostInfoResponseDto;
import likelion.univ.adminPost.usecase.GetPostsByCategoriesAndUniversityUseCase;
import likelion.univ.common.response.PageResponse;
import likelion.univ.domain.post.dto.enums.MainCategory;
import likelion.univ.domain.post.dto.enums.SubCategory;
import likelion.univ.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/v1/univAdmin")
@RequiredArgsConstructor
@Tag(name = "UnivPost", description = "학교 대표 게시글 API")
public class RepresentativePostController {

    private final GetPostsByCategoriesAndUniversityUseCase getPostsByCategoriesAndUniversityUseCase;

    @Operation(summary = "카테고리 별 게시글 조회")
    @GetMapping("/posts")
    public SuccessResponse<Object> getProjectsOfFreeBoardPosts(Pageable pageable,
                                                               @RequestParam MainCategory mainCategory,
                                                               @RequestParam SubCategory subCategory){
        PageResponse<PostInfoResponseDto> response = getPostsByCategoriesAndUniversityUseCase.execute(pageable,
                mainCategory, subCategory);
        return SuccessResponse.of(response);
    }

}
