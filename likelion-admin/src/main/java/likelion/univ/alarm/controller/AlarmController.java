package likelion.univ.alarm.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import likelion.univ.alarm.dto.EmailAlarmDto;
import likelion.univ.alarm.dto.GetRecruitsDto;
import likelion.univ.alarm.dto.RecruitEmailAlarmDto;
import likelion.univ.alarm.dto.UserListDto;
import likelion.univ.alarm.usecase.*;
import likelion.univ.domain.recruit.service.RecruitService;
import likelion.univ.domain.user.entity.User;
import likelion.univ.domain.user.repository.searchcondition.UserSearchCondition;
import likelion.univ.response.SuccessResponse;
import likelion.univ.utils.AuthenticatedUserUtils;
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
    private final DeleteRecruitUsecase deleteRecruitUsecase;

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
            summary = "리크루팅 조회 API",
            description = "대학 대표가 등록된 리크루팅 명단을 확인할 수 있는 API 입니다."
    )
    public SuccessResponse<GetRecruitsDto> getRecruits(@RequestParam int generation) {
        GetRecruitsDto response = getRecruitsUsecase.execute(generation);

        return SuccessResponse.of(response);
    }

    @DeleteMapping("/recruit/{id}")
    @Operation(
            summary = "리크루팅 삭제 API",
            description = "대학 대표가 등록된 리크루팅 명단을 삭제할 수 있는 API 입니다."
    )
    public SuccessResponse<Long> deleteRecruit(@PathVariable Long id) {
        deleteRecruitUsecase.execute(id);
        return SuccessResponse.of(id);
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
