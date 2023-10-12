package likelion.univ.post.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import likelion.univ.domain.post.dto.response.PostDetailResponseDto;
import likelion.univ.domain.post.dto.response.PostCommandResponseDto;
import likelion.univ.domain.post.entity.enums.MainCategory;
import likelion.univ.domain.post.entity.enums.SubCategory;
import likelion.univ.post.dto.PostCreateRequestDto;
import likelion.univ.post.dto.PostUpdateRequestDto;
import likelion.univ.post.repository.PostReadRepository;
import likelion.univ.post.usecase.*;
import likelion.univ.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/community/posts")
@Tag(name = "게시글", description = "커뮤니티, 마이페이지 APIs")
public class PostController {

    private final PostCreateUseCase postCreateUseCase;
    private final PostUpdateUseCase postUpdateUsecase;
    private final PostDeleteUseCase postDeleteUseCase;
    private final PostReadRepository postReadRepository;

    /* read */
    @Operation(summary = "(커뮤니티) 카테고리별 posts 최신순 조회", description = "카테고리 params\n- maincatstr : 메인 카테고리 문자열(HQ_BOARD, FREE_BOARD, OVERFLOW)\n- subcatstr : 서브 카테고리 문자열(post 생성 참고)\n\n페이지네이션 params\n- page : 0 이상의 정수 \n- size : 양수")
    @GetMapping("")
    public SuccessResponse<?> findCategorizedPosts(@RequestParam String maincatstr, @RequestParam String subcatstr, @RequestParam Integer page, @RequestParam Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        MainCategory mainCategory = MainCategory.valueOf(maincatstr);
        SubCategory subCategory = SubCategory.valueOf(subcatstr);

        List<PostDetailResponseDto> response = postReadRepository.findAll(mainCategory, subCategory, pageRequest);
        return SuccessResponse.of(response);
    }
    @Operation(summary = "(마이페이지) 유저별 posts 최신순 조회", description = "유저Id를 param으로 넣어서, 유저별로 작성한 게시글을 최신순으로 조회 \n- page : 0 이상의 정수 \n- size : 양수")
    @GetMapping("/mypage/{userId}")
    public SuccessResponse<?> findAuthorPosts(@PathVariable Long userId, @RequestParam Integer page, @RequestParam Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<PostDetailResponseDto> response = postReadRepository.findAuthorPosts(userId, pageRequest);
        return SuccessResponse.of(response);
    }

    @Operation(summary = "(마이페이지) 유저가 댓글을 단 posts 최신순 조회", description = "(로그인된 유저 기준 only) 댓글 단 posts 최신순 조회 \n- page : 0 이상의 정수 \n- size : 양수")
    @GetMapping("/mypage/commented")
    public SuccessResponse<?> findCommentedPosts(@RequestParam Integer page, @RequestParam Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<PostDetailResponseDto> response = postReadRepository.findCommentedPosts(pageRequest);
        return SuccessResponse.of(response);
    }

    @Operation(summary = "(마이페이지) 유저가 좋아요한 posts 최신순 조회", description = "(로그인된 유저 기준 only) 좋아요를 누른 posts 최신순 조회 \n- page : 0 이상의 정수 \n- size : 양수")
    @GetMapping("/mypage/liked")
    public SuccessResponse<?> findLikedPosts(@RequestParam Integer page, @RequestParam Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<PostDetailResponseDto> response = postReadRepository.findLikedPosts(pageRequest);
        return SuccessResponse.of(response);
    }

    /* command */
    @Operation(summary = "게시글을 생성", description = "Main Category\n- HQ_BOARD(멋대 중앙)\n- FREE_BOARD(자유게시판)\n- OVERFLOW(멋사 오버플로우) \n\nSub Category\n- HQ_BOARD : NOTICE(공지사항), QNA(질문건의), HQ_INFO(정보공유)\n- FREE_BOARD : FREE_INFO(정보공유), GET_MEMBER(팀원구함), GET_PROJECT(프로젝트 구함), SHOWOFF(프로젝트 자랑)\n- OVERFLOW : FRONTEND(프론트엔드), BACKEND(백엔드), PM(기획), UXUI(디자인), ETC(기타)")
    @PostMapping("/new")
    public SuccessResponse<?> createPost(@RequestBody @Valid PostCreateRequestDto request/*, BindingResult bindingResult*/) {
        PostCommandResponseDto response = postCreateUseCase.execute(request);
        return SuccessResponse.of(response);
    }
    @Operation(summary = "게시글 수정", description = "제목, 내용, 썸네일 수정 : 수정을 안하는 값은 기존 데이터로 넘겨줘야 함")
    @PatchMapping("/{postId}")
    public SuccessResponse<?> updatePost(@PathVariable Long postId, @RequestBody PostUpdateRequestDto request) {
        PostCommandResponseDto response = postUpdateUsecase.execute(postId, request);
        return SuccessResponse.of(response);
    }

    @Operation(summary = "게시글 hard delete", description = "게시글을 database로부터 hard delete")
    @DeleteMapping("/{postId}")
    public SuccessResponse<?> deletePost(@PathVariable Long postId) {
        postDeleteUseCase.execute(postId);
        return SuccessResponse.empty();
    }

}
