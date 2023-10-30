package likelion.univ.mypage.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import likelion.univ.common.page.PageResponse;
import likelion.univ.mypage.dto.response.MyPagePostsDto;
import likelion.univ.mypage.dto.response.ProfileDetailsDto;
import likelion.univ.mypage.usecase.GetMyPostsUseCase;
import likelion.univ.mypage.usecase.GetPostsCommentedByMeUseCase;
import likelion.univ.mypage.usecase.GetProfileUseCase;
import likelion.univ.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/mypage")
@Tag(name = "마이페이지", description = "마이페이지관련 API입니다.")
public class MypageController {
    private final GetProfileUseCase getProfileUseCase;
    private final GetMyPostsUseCase getMyPostsUseCase;
    private final GetPostsCommentedByMeUseCase getPostsCommentedByMeUseCase;

    @Operation(summary = "마이페이지 프로필 조회", description = "본인의 프로필 정보를 조회합니다.")
    @GetMapping("/profile")
    public SuccessResponse<Object> getProfile(){
        ProfileDetailsDto profileDetailsDto = getProfileUseCase.execute();
        return SuccessResponse.of(profileDetailsDto);
    }

    @Operation(summary = "내가쓴 게시글 조회", description = "작성한 게시글을 조회합니다.")
    @GetMapping("/posts")
    public SuccessResponse<Object> getMyPosts(@ParameterObject @PageableDefault(size = 6, page = 1) Pageable pageable){
        PageResponse<MyPagePostsDto> myPagePostsPage = getMyPostsUseCase.execute(pageable);
        return SuccessResponse.of(myPagePostsPage);
    }
    @Operation(summary = "내가 댓글 쓴 게시글 조회", description = "댓글을 작성한 게시글을 조회합니다.")
    @GetMapping("/comments")
    public SuccessResponse<Object> getPostsCommentedByMe(@ParameterObject @PageableDefault(size = 6, page = 1) Pageable pageable){
        PageResponse<MyPagePostsDto> myPagePostsPageCommentedByMe = getPostsCommentedByMeUseCase.execute(pageable);
        return SuccessResponse.of(myPagePostsPageCommentedByMe);
    }

//    @Operation(summary = "내가 참여한 프로젝트 조회", description = "참여한 프로젝트를 조회합니다.")
//    @GetMapping("/projects")
//    public SuccessResponse<Object> getMyProjects(@ParameterObject @PageableDefault(size = 6, page = 1) Pageable pageable){
//
//        return SuccessResponse.of();
//    }
//
//    @Operation(summary = "휴대폰 인증 요청", description = "휴대폰 번호 인증을 위해 서버 내부에 인증번호를 생성합니다.")
//    @PostMapping("/phone/certify")
//    public SuccessResponse<Object> login(@RequestParam("phonenum") String phoneNum){
//        return SuccessResponse.of();
//    }
//
//    @Operation(summary = "휴대폰 인증번호 확인", description = "휴대폰 인증번호 확인 후 인증 토큰을 발급합니다.")
//    @PostMapping("/phone/certify/check")
//    public SuccessResponse<Object> getIdToken(@RequestParam("certifynum") Long certifyNum){
//
//        return SuccessResponse.of();
//    }

}

