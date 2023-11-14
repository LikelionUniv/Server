package likelion.univ.follow.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import likelion.univ.follow.usecase.CancelFollowUseCase;
import likelion.univ.follow.usecase.FollowUserUseCase;
import likelion.univ.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/follow")
@Tag(name = "팔로우", description = "팔로우 관련 API입니다.")
public class FollowController {
    private final FollowUserUseCase followUserUseCase;
    private final CancelFollowUseCase cancelFollowUseCase;

    @Operation(summary = "팔로우 ", description = "해당 유저를 팔로우 합니다.")
    @PostMapping("/{userId}")
    public SuccessResponse<Object> follow(@PathVariable("userId") Long userId){
        followUserUseCase.execute(userId);
        return SuccessResponse.empty();
    }

    @Operation(summary = "팔로우 취소", description = "해당 유저를 팔로우 취소 합니다.")
    @DeleteMapping("/{userId}")
    public SuccessResponse<Object> deleteFollow(@PathVariable("userId") Long userId) {
        cancelFollowUseCase.execute(userId);
        return SuccessResponse.empty();
    }
}

