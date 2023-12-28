package likelion.univ.adminPost.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import likelion.univ.adminPost.dto.response.PostInfoResponseDto;
import likelion.univ.adminPost.usecase.GetPostsByCategoriesAndUniversityUseCase;
import likelion.univ.common.response.PageResponse;
import likelion.univ.domain.post.dto.enums.MainCategory;
import likelion.univ.domain.post.dto.enums.SubCategory;
import likelion.univ.post.usecase.DeletePostUseCase;
import likelion.univ.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.client.AbstractClientHttpRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static likelion.univ.response.SuccessResponse.empty;

@Slf4j
@RestController("adminPost.controller.RepresentativeController")
@RequestMapping(value = "/v1/univAdmin")
@RequiredArgsConstructor
@Tag(name = "PostByUnivAdmin", description = "학교 대표에 의한 게시글 관련 API")
public class RepresentativeController {

    private final DeletePostUseCase deletePostUseCase;
    private final GetPostsByCategoriesAndUniversityUseCase getPostsByCategoriesAndUniversityUseCase;

    @Operation(summary = "카테고리 별 게시글 조회")
    @GetMapping("/posts")
    public SuccessResponse<Object> getProjectsOfFreeBoardPosts(Pageable pageable,
                                                               @RequestParam MainCategory mainCategory,
                                                               @RequestParam SubCategory subCategory) {
        PageResponse<PostInfoResponseDto> response = getPostsByCategoriesAndUniversityUseCase.execute(pageable,
                mainCategory, subCategory);
        return SuccessResponse.of(response);
    }

    @Operation(summary = "단일 게시글 삭제")
    @DeleteMapping("/deleteSelectedPosts")
    public SuccessResponse<Object> deletePosts(@RequestParam Long postId) {

        deletePostUseCase.execute(postId);
        return SuccessResponse.empty();
    }
}

