package likelion.univ.post.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import likelion.univ.domain.post.dto.response.PostSimpleData;
import likelion.univ.domain.post.dto.response.PostIdData;
import likelion.univ.domain.post.entity.enums.MainCategory;
import likelion.univ.domain.post.entity.enums.SubCategory;
import likelion.univ.post.dto.request.PostCreateRequestDto;
import likelion.univ.post.dto.request.PostUpdateRequestDto;
import likelion.univ.post.dto.response.PostDetailResponseDto;
import likelion.univ.post.usecase.*;
import likelion.univ.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
@Tag(name = "게시글", description = "커뮤니티 APIs")
public class PostController {

    private final CreatePostUseCase createPostUseCase;
    private final EditPostUseCase editPostUsecase;
    private final DeletePostUseCase deletePostUseCase;
    private final GetPostsByCategoriesUseCase getPostsByCategoriesUseCase;
    private final GetPostDetailUseCase getPostDetailUseCase;

    /* ----- read ----- */
    @Operation(
            summary = "게시글 단일 조회",
            description =
                    "### 게시글 상세 조회 api입니다.\n" +
                    "- 테스트 완료(황제철)" +
                    "- 게시글 / 댓글에 profile image url이 없으면 boolean 타입만 전달 (url : null 포함x)")
    @GetMapping("/community/posts/{postId}")
    public SuccessResponse<PostDetailResponseDto> findPostDetail(@PathVariable Long postId) {
        PostDetailResponseDto response = getPostDetailUseCase.execute(postId);
        return SuccessResponse.of(response);
    }

    @Operation(
            summary = "카테고리별 posts 조회",
            description =
            "### 카테고리 params\n" +
                "- mc : 메인 카테고리 문자열(HQ_BOARD, FREE_BOARD, OVERFLOW)\n" +
                "- sc : 서브 카테고리 문자열(post 생성 참고)\n\n" +
            "### 페이지네이션 params\n" +
                "- p : (page ; 페이지 넘버) 1 이상의 정수\n" +
                "- s : (size ; 페이지 크기) 양수\n\n" +
            "### 항목 선택\n" +
                "- 최신 순 (생성 일자 기준 내림차순)\n" +
                "- 좋아요 순 (좋아요 수 기준 내림차순)\n" +
                "- 댓글 순 (댓글 수 기준 내림차순)"
    )
    @GetMapping("/community/posts/")
    public SuccessResponse<List<PostSimpleData>> findCategorizedPosts(@RequestParam MainCategory mc, @RequestParam SubCategory sc,
                                                                      @ParameterObject @PageableDefault(size = 5, page = 1) Pageable pageable) {
//        MainCategory mainCategory = MainCategory.valueOf(mc);
//        SubCategory subCategory = SubCategory.valueOf(sc);

        // usecase -> service -> repository
        List<PostSimpleData> response = getPostsByCategoriesUseCase.execute(mc, sc, pageable);
        return SuccessResponse.of(response);
    }


    /* ----- command ----- */
    @Operation(
            summary = "게시글을 생성",
            description =
            "Main Category\n" +
                    "- HQ_BOARD(멋대 중앙)\n" +
                    "- FREE_BOARD(자유게시판)\n" +
                    "- OVERFLOW(멋사 오버플로우) \n\n" +
            "Sub Category\n" +
                    "- HQ_BOARD : NOTICE(공지사항), QNA(질문건의), HQ_INFO(정보공유)\n" +
                    "- FREE_BOARD : FREE_INFO(정보공유), GET_MEMBER(팀원구함), GET_PROJECT(프로젝트 구함), SHOWOFF(프로젝트 자랑)\n" +
                    "- OVERFLOW : FRONTEND(프론트엔드), BACKEND(백엔드), PM(기획), UXUI(디자인), ETC(기타)")
    @PostMapping("/community/posts/new")
    public SuccessResponse<PostIdData> createPost(@RequestBody @Valid PostCreateRequestDto request/*, BindingResult bindingResult*/) {
        PostIdData response = createPostUseCase.execute(request);
        return SuccessResponse.of(response);
    }
    @Operation(
            summary = "게시글 수정",
            description = "제목, 내용, 썸네일 수정 : 수정을 안하는 값은 기존 데이터로 넘겨줘야 함")
    @PatchMapping("/community/posts/{postId}")
    public SuccessResponse<PostIdData> updatePost(@PathVariable Long postId, @RequestBody PostUpdateRequestDto request) {
        PostIdData response = editPostUsecase.execute(postId, request);
        return SuccessResponse.of(response);
    }

    @Operation(
            summary = "게시글 hard delete",
            description = "게시글을 database로부터 hard delete")
    @DeleteMapping("/community/posts/{postId}")
    public SuccessResponse<? extends PostIdData> deletePost(@PathVariable Long postId) {
        deletePostUseCase.execute(postId);
        return SuccessResponse.empty();
    }

}
