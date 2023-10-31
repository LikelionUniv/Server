package likelion.univ.mypage.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import likelion.univ.common.page.PageResponse;
import likelion.univ.mypage.dto.response.UserPagePostsDto;
import likelion.univ.mypage.dto.response.ProfileDetailsDto;
import likelion.univ.mypage.usecase.GetUserPostsUseCase;
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
@RequestMapping(value = "/v1/user")
@Tag(name = "유저페이지", description = "유저페이지관련 API입니다.")
public class UserController {
    private final GetProfileUseCase getProfileUseCase;
    private final GetUserPostsUseCase getMyPostsUseCase;
    private final GetPostsCommentedByMeUseCase getPostsCommentedByMeUseCase;

    @Operation(summary = "유저페이지 프로필 조회", description = "해당 유저의 프로필 정보를 조회합니다.")
    @GetMapping("/{userId}/profile")
    public SuccessResponse<Object> getProfile(@PathVariable Long userId){
        ProfileDetailsDto profileDetailsDto = getProfileUseCase.execute(userId);
        return SuccessResponse.of(profileDetailsDto);
    }

    @Operation(summary = "해당 유저가 쓴 게시글 조회", description = "해당 유저가 작성한 게시글을 조회합니다.")
    @GetMapping("/{userId}/posts")
    public SuccessResponse<Object> getMyPosts(@PathVariable Long userId,
                                              @ParameterObject @PageableDefault(size = 6, page = 1) Pageable pageable){
        PageResponse<UserPagePostsDto> myPagePostsPage = getMyPostsUseCase.execute(userId, pageable);
        return SuccessResponse.of(myPagePostsPage);
    }
    @Operation(summary = "해당 유저가 댓글 쓴 게시글 조회", description = "해당 유저가 댓글을 작성한 게시글을 조회합니다.")
    @GetMapping("/{userId}/comments")
    public SuccessResponse<Object> getPostsCommentedByMe(@PathVariable Long userId,
                                                         @ParameterObject @PageableDefault(size = 6, page = 1) Pageable pageable){
        PageResponse<UserPagePostsDto> myPagePostsPageCommentedByMe = getPostsCommentedByMeUseCase.execute(userId, pageable);
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

