package likelion.univ.alarm.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import likelion.univ.alarm.dto.*;
import likelion.univ.alarm.usecase.*;
import likelion.univ.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "Alarm", description = "알람을 보내는 API")
@RestController
@RequestMapping(value = "/v1/alarm", produces = "application/json")
@RequiredArgsConstructor
public class AlarmController {

    private final SendEmailUseCase sendEmailUseCase;
    private final GetAlarmsUseCase getAlarmsUseCase;
    private final DeleteAlarmUseCase deleteAlarmUseCase;

    @PostMapping(
            value = "/email" ,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE}
    )
    @Operation(
            summary = "이메일 알람 전송 API",
            description = "이메일 알람을 보내는 API 입니다."
    )
    public SuccessResponse<String> sendEmail(
            @RequestBody SendEmailDto sendEmailDto,
            @RequestPart(required = false) List<MultipartFile> attachments) {
        sendEmailUseCase.execute(sendEmailDto, attachments);
        return SuccessResponse.of("리크루팅 알람 전송 성공");
    }

    @GetMapping
    @Operation(
            summary = "알람 등록 조회 API",
            description = "알람 전송을 희망하는 분들을 조회하는 API 입니다."
    )
    public SuccessResponse<GetAlarmsDto> getAlarms(@RequestParam Long ordinal) {
        GetAlarmsDto response = getAlarmsUseCase.execute(ordinal);
        return SuccessResponse.of(response);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "알람 삭제 API",
            description = "등록된 알람을 삭제하는 API 입니다."
    )
    public SuccessResponse<Long> deleteAlarm(@PathVariable Long id) {
        deleteAlarmUseCase.execute(id);
        return SuccessResponse.of(id);
    }
}
