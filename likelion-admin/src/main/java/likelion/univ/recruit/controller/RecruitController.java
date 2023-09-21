package likelion.univ.recruit.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import likelion.univ.recruit.dto.RecruitRegisterDto;
import likelion.univ.recruit.usecase.CreateRecruitUsecase;
import likelion.univ.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/recruit")
@Tag(name = "recruit", description = "리크루팅 알람 API")
public class RecruitController {

    private final CreateRecruitUsecase createRecruitUsecase;

    @PostMapping("/{universityName}")
    @Operation(summary = "리크루팅 알람 등록 API", description = "리크루팅 알람을 등록하기 위해 사용하는 API 입니다.")
    public SuccessResponse<Long> register(
            @RequestBody RecruitRegisterDto recruitRegisterDto,
            @PathVariable String universityName) {
        Long recruitId = createRecruitUsecase.execute(recruitRegisterDto, universityName);
        return SuccessResponse.of(recruitId);
    }
}
