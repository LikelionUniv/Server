package likelion.univ.alarm.controller;

import likelion.univ.alarm.dto.EmailContentsDto;
import likelion.univ.alarm.usecase.EmailAlarmUsecase;
import likelion.univ.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/alarm", produces = "application/json")
@RequiredArgsConstructor
public class AlarmController {

    private final EmailAlarmUsecase emailAlarmUsecase;

    @PatchMapping("/email")
    public SuccessResponse<String> emailAlarm(
            @RequestBody EmailContentsDto emailContentsDto,
            @RequestParam String type) {
        emailAlarmUsecase.execute(emailContentsDto);
        return SuccessResponse.of("알람 전송 성공");
    }
}
