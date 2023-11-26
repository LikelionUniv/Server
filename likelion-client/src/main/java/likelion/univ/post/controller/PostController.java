package likelion.univ.post.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import likelion.univ.domain.post.dto.response.PostDetailResponseDto;
import likelion.univ.domain.post.dto.response.PostCommandResponseDto;
import likelion.univ.domain.post.entity.enums.MainCategory;
import likelion.univ.domain.post.entity.enums.SubCategory;
import likelion.univ.post.dto.PostCreateRequestDto;
import likelion.univ.post.dto.PostUpdateRequestDto;
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
@Tag(name = "게시글", description = "커뮤니티, 마이페이지 APIs")
public class PostController {

    private final CreatePostUseCase createPostUseCase;
    private final EditPostUseCase editPostUsecase;
    private final DeletePostUseCase deletePostUseCase;
    private final GetLatestPostsUseCase getLatestPostsUseCase;
    private final GetMyPostsUseCase getMyPostsUseCase;
    private final GetCommentedPostsUseCase getCommentedPostsUseCase;
    private final GetLikedPostsUseCase getLikedPostsUseCase;

    /* read */
    @Operation(
            summary = "(커뮤니티) 카테고리별 posts 최신순 조회",
            description =
            "카테고리 params\n" +
                "- mc : 메인 카테고리 문자열(HQ_BOARD, FREE_BOARD, OVERFLOW)\n" +
                "- sc : 서브 카테고리 문자열(post 생성 참고)\n\n" +
            "페이지네이션 params\n" +
                "- p : (page ; 페이지 넘버) 0 이상의 정수 \n" +
                "- s : (size ; 페이지 크기) 양수")
    @GetMapping("/community/posts")
    public SuccessResponse<?> findCategorizedPosts(@RequestParam String mc, @RequestParam String sc,
                                                   @ParameterObject @PageableDefault(size = 5, page = 1) Pageable pageable) {
        MainCategory mainCategory = MainCategory.valueOf(mc);
        SubCategory subCategory = SubCategory.valueOf(sc);

        // usecase -> service -> repository
        List<PostDetailResponseDto> response = getLatestPostsUseCase.execute(mainCategory, subCategory, pageable);
        return SuccessResponse.of(response);
    }
    @Operation(
            summary = "(마이페이지) 작성 유저별 posts 최신순 조회",
            description =
            "유저Id를 param으로 넣어서, 유저별로 작성한 게시글을 최신순으로 조회 \n" +
                "- page : 0 이상의 정수 \n" +
                "- size : 양수")
    @GetMapping("/community/posts/mypage/author/{userId}")
    public SuccessResponse<?> findAuthorPosts(@PathVariable Long userId,
                                              @ParameterObject @PageableDefault(size = 5, page = 1) Pageable pageable) {
        List<PostDetailResponseDto> response = getMyPostsUseCase.execute(userId, pageable);
        return SuccessResponse.of(response);
    }

    // TODO : (마이페이지) 로그인 유저가 작성한 posts 최신순 조회

    @Operation(
            summary = "(마이페이지) \"로그인 유저\"가 댓글을 단 posts 최신순 조회",
            description =
            "(로그인된 유저 기준 only) 댓글 단 posts 최신순 조회 \n" +
                    "- page : 0 이상의 정수 \n" +
                    "- size : 양수")
    @GetMapping("/community/posts/mypage/commented")
    public SuccessResponse<?> findCommentedPosts(@ParameterObject @PageableDefault(size = 5, page = 1) Pageable pageable) {
        List<PostDetailResponseDto> response = getCommentedPostsUseCase.execute(pageable);
        return SuccessResponse.of(response);
    }

    @Operation(
            summary = "(마이페이지) \"로그인 유저\"가 좋아요한 posts 최신순 조회",
            description =
            "(로그인된 유저 기준 only) 좋아요를 누른 posts 최신순 조회 \n" +
                    "- page : 0 이상의 정수 \n" +
                    "- size : 양수")
    @GetMapping("/community/posts/mypage/liked")
    public SuccessResponse<?> findLikedPosts(@ParameterObject @PageableDefault(size = 5, page = 1) Pageable pageable) {
        List<PostDetailResponseDto> response = getLikedPostsUseCase.execute(pageable);
        return SuccessResponse.of(response);
    }

    /* command */
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
    public SuccessResponse<?> createPost(@RequestBody @Valid PostCreateRequestDto request/*, BindingResult bindingResult*/) {
        PostCommandResponseDto response = createPostUseCase.execute(request);
        return SuccessResponse.of(response);
    }
    @Operation(
            summary = "게시글 수정",
            description = "제목, 내용, 썸네일 수정 : 수정을 안하는 값은 기존 데이터로 넘겨줘야 함")
    @PatchMapping("/community/posts/{postId}")
    public SuccessResponse<?> updatePost(@PathVariable Long postId, @RequestBody PostUpdateRequestDto request) {
        PostCommandResponseDto response = editPostUsecase.execute(postId, request);
        return SuccessResponse.of(response);
    }

    @Operation(
            summary = "게시글 hard delete",
            description = "게시글을 database로부터 hard delete")
    @DeleteMapping("/community/posts/{postId}")
    public SuccessResponse<?> deletePost(@PathVariable Long postId) {
        deletePostUseCase.execute(postId);
        return SuccessResponse.empty();
    }

}
