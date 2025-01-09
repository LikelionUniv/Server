package likelion.univ.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import likelion.univ.common.response.PageResponse;
import likelion.univ.common.response.SliceResponse;
import likelion.univ.response.SuccessResponse;
import likelion.univ.user.dto.request.IssueGraduationRequest;
import likelion.univ.user.dto.request.ProfileEditRequestDto;
import likelion.univ.user.dto.response.*;
import likelion.univ.user.service.ClientUserQueryService;
import likelion.univ.user.service.ClientUserService;
import likelion.univ.utils.AuthenticatedUserUtils;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/user")
@Tag(name = "유저페이지", description = "유저페이지관련 API입니다.")
public class UserController {
    private final AuthenticatedUserUtils userUtils;
    private final ClientUserService userService;
    private final ClientUserQueryService userQueryService;

    @Operation(summary = "유저페이지 프로필 조회", description = "해당 유저의 프로필 정보를 조회합니다.")
    @GetMapping("/{userId}/profile")
    public SuccessResponse<ProfileDetailsDto> getProfile(
            @PathVariable("userId") Long userId
    ) {
        ProfileDetailsDto profileDetailsDto = userQueryService.getProfile(userId);
        return SuccessResponse.of(profileDetailsDto);
    }

    @Operation(summary = "유저페이지 프로필 수정", description = "해당 유저의 프로필 정보를 수정합니다.")
    @PatchMapping("/{userId}/profile")
    public SuccessResponse editProfile(
            @PathVariable("userId") Long userId,
            @RequestBody ProfileEditRequestDto profileEditRequestDto
    ) {
        userService.editProfile(userId, profileEditRequestDto);
        return SuccessResponse.empty();
    }

    @Operation(summary = "팔로잉 목록 조회", description = "해당 유저의 팔로잉 목록을 조회합니다.")
    @GetMapping("/{userId}/following")
    public SuccessResponse<SliceResponse<FollowUserInfoDto>> getFollowingList(
            @PathVariable("userId") Long userId,
            @ParameterObject @PageableDefault(size = 4, page = 0) Pageable pageable
    ) {
        SliceResponse<FollowUserInfoDto> followingUsers = userQueryService.getFollowInfoForFollowing(userId, pageable);
        return SuccessResponse.of(followingUsers);
    }

    @Operation(summary = "팔로워 목록 조회", description = "해당 유저의 팔로워 목록을 조회합니다.")
    @GetMapping("/{userId}/follower")
    public SuccessResponse<SliceResponse<FollowUserInfoDto>> getFollowerList(
            @PathVariable("userId") Long userId,
            @ParameterObject @PageableDefault(size = 4, page = 0) Pageable pageable
    ) {
        SliceResponse<FollowUserInfoDto> followerUsers = userQueryService.getFollowInfoForFollower(userId, pageable);
        return SuccessResponse.of(followerUsers);
    }

    @Operation(summary = "해당 유저가 쓴 게시글 조회", description = "해당 유저가 작성한 게시글을 조회합니다.")
    @GetMapping("/{userId}/posts")
    public SuccessResponse<PageResponse<UserPagePostsDto>> getPostsWrittenByUser(
            @PathVariable("userId") Long userId,
            @ParameterObject @PageableDefault(size = 6, page = 1) Pageable pageable
    ) {
        PageResponse<UserPagePostsDto> myPagePostsPage = userQueryService.getUserPosts(userId, pageable);
        return SuccessResponse.of(myPagePostsPage);
    }

    @Operation(summary = "해당 유저가 좋아요 누른 게시글 조회", description = "해당 유저가 좋아요를 누른 게시글을 조회합니다. 파라미터로 search를 포함하지 않을 시 전체 조회입니다.")
    @GetMapping("/{userId}/posts/like")
    public SuccessResponse<PageResponse<UserPagePostsDto>> getPostsLikedByUser(
            @PathVariable("userId") Long userId,
            @RequestParam(value = "sort", required = false, defaultValue = "created_date") String sort,
            @RequestParam(value = "search", required = false) String search,
            @ParameterObject @PageableDefault(size = 6, page = 0) Pageable pageable
    ) {
        PageResponse<UserPagePostsDto> myPagePostsPageLikedByUser = userQueryService.getUserLikedPosts(
                userId, pageable, sort, search
        );
        return SuccessResponse.of(myPagePostsPageLikedByUser);
    }

    @Operation(summary = "해당 유저가 댓글 쓴 게시글 조회", description = "해당 유저가 댓글을 작성한 게시글을 조회합니다.")
    @GetMapping("/{userId}/posts/comment")
    public SuccessResponse<PageResponse<UserPagePostsDto>> getPostsCommentedByUser(
            @PathVariable("userId") Long userId,
            @ParameterObject @PageableDefault(size = 6, page = 0) Pageable pageable
    ) {
        PageResponse<UserPagePostsDto> myPagePostsPageCommentedByUser = userQueryService.getPostsCommentedByMe(
                userId, pageable
        );
        return SuccessResponse.of(myPagePostsPageCommentedByUser);
    }

    @Operation(summary = "유저 검색 (Simple Data) (project page)", description = "이름으로 유저를 검색합니다. (프로젝트 페이지 모달)")
    @GetMapping("/search")
    public SuccessResponse<SliceResponse<UserSearchResultDto>> searchUser(
            @RequestParam(value = "name", required = false) String name,
            @ParameterObject @PageableDefault(size = 4, page = 0) Pageable pageable
    ) {
        SliceResponse<UserSearchResultDto> searchedUsers = userQueryService.searchUserByName(name, pageable);
        return SuccessResponse.of(searchedUsers);
    }

    @Operation(summary = "내가 참여한 프로젝트 조회", description = "참여한 프로젝트를 조회합니다.")
    @GetMapping("/{userId}/projects")
    public SuccessResponse<PageResponse<UserPageProjectsDto>> getMyProjects(
            @PathVariable("userId") Long userId,
            @ParameterObject @PageableDefault(size = 6, page = 0) Pageable pageable
    ) {
        PageResponse<UserPageProjectsDto> myPageProjects = userQueryService.getUserProjects(userId, pageable);
        return SuccessResponse.of(myPageProjects);
    }

    @Operation(summary = "수료증 발급 API", description = "수료증 PDF를 발급합니다.")
    @PostMapping("/graduations/issue")
    public SuccessResponse<IssueGraduationResponse> issueGraduations(
            @RequestBody @Valid IssueGraduationRequest request
    ) {
        Long userId = userUtils.getCurrentUserId();
        String result = userService.generateGraduationPdf(userId, request.ordinal());
        return SuccessResponse.of(IssueGraduationResponse.from(result));
    }
}

