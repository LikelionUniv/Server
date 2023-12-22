package likelion.univ.alarm.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import likelion.univ.alarm.dto.EmailAlarmDto;
import likelion.univ.alarm.dto.GetRecruitsDto;
import likelion.univ.alarm.dto.RecruitEmailAlarmDto;
import likelion.univ.alarm.dto.UserListDto;
import likelion.univ.alarm.usecase.EmailAlarmUsecase;
import likelion.univ.alarm.usecase.EmailRecruitAlarmUsecase;
import likelion.univ.alarm.usecase.GetRecruitsUsecase;
import likelion.univ.alarm.usecase.GetUsersUsecase;
import likelion.univ.domain.user.entity.User;
import likelion.univ.domain.user.repository.searchcondition.UserSearchCondition;
import likelion.univ.response.SuccessResponse;
import likelion.univ.utils.AuthentiatedUserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Alarm", description = "알람을 보내는 API")
@RestController
@RequestMapping(value = "/v1/alarm", produces = "application/json")
@RequiredArgsConstructor
public class AlarmController {

    private final EmailAlarmUsecase emailAlarmUsecase;
    private final EmailRecruitAlarmUsecase emailRecruitAlarmUsecase;
    private final GetRecruitsUsecase getRecruitsUsecase;
    private final GetUsersUsecase getUsersUsecase;
    private final AuthentiatedUserUtils userUtils;

    @PostMapping
    @Operation(
            summary = "관리자용 알람 전송 API",
            description = "관리자(SUPER_ADMIN)가 이메일 알람을 보내는 API 입니다."
    )
    public SuccessResponse<String> sendAlarm(@RequestBody EmailAlarmDto emailAlarmDto) {
        emailAlarmUsecase.execute(emailAlarmDto);
        return SuccessResponse.of("알람 전송 성공");

    }

    @PostMapping("/recruit")
    @Operation(
            summary = "리크루팅 알람 전송 API",
            description = "대학 대표(UNIVERSITY_ADMIN)가 리크루팅 이메일 알람을 보내는 API 입니다."
    )
    public SuccessResponse<String> sendRecruitAlarm(@RequestBody RecruitEmailAlarmDto recruitEmailAlarmDto) {
        emailRecruitAlarmUsecase.execute(recruitEmailAlarmDto);
        return SuccessResponse.of("리크루팅 알람 전송 성공");
    }

    @GetMapping("/recruit")
    @Operation(
            summary = "리크로팅 조회 API",
            description = ""
    )
    public SuccessResponse<GetRecruitsDto> getRecruits(@RequestParam int generation) {
        User universityManager = userUtils.getCurrentUser();
        GetRecruitsDto response = getRecruitsUsecase.execute(universityManager, generation);

        return SuccessResponse.of(response);
    }

    @GetMapping("/user")
    @Operation(
            summary = "유저 정보 요청 API",
            description = "알람 전송 전 어떤 유저에게 전송할지 선택하기 위해 모든 유저를 불러오는 API 입니다.\n"
                    + "유저 이름, 대학 이름, 파트 별로 필터링 가능 하며 전체 결과를 원할시 빈 문자열 혹은 파라미터를 생략하시면 됩니다.")
    public SuccessResponse<UserListDto> getUsers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String university,
            @RequestParam(required = false) String part) {
        UserSearchCondition condition = new UserSearchCondition(name, university, part);
        UserListDto users = getUsersUsecase.execute(condition);

        return SuccessResponse.of(users);
    }
}
