package likelion.univ.recruit.controller;

import likelion.univ.recruit.controller.enums.AlarmType;
import likelion.univ.recruit.dto.RecruitAlarmContentDto;
import likelion.univ.recruit.dto.RecruitRegisterDto;
import likelion.univ.recruit.usecase.CreateRecruitUsecase;
import likelion.univ.recruit.usecase.RecruitAlarmUsecase;
import likelion.univ.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/recruit")
public class RecruitController {

    private final CreateRecruitUsecase createRecruitUsecase;

    @PostMapping("/{universityName}")
    public SuccessResponse<Long> register(
            @RequestBody RecruitRegisterDto recruitRegisterDto,
            @PathVariable String universityName) {
        Long recruitId = createRecruitUsecase.execute(recruitRegisterDto, universityName);
        return SuccessResponse.of(recruitId);
    }

    @PatchMapping("/alarm/{universityId}")
    public SuccessResponse<String> alarm(
            @PathVariable Long universityId,
            @RequestBody RecruitAlarmContentDto recruitAlarmContentDto,
            @RequestParam String type) {

        RecruitAlarmUsecase alarmUsecase = AlarmType.of(type);
        alarmUsecase.execute(recruitAlarmContentDto, universityId);
        return SuccessResponse.of("알람 전송 성공");
    }

}
