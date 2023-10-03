package likelion.univ.university.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import likelion.univ.university.dto.response.UnivResponseDto;
import likelion.univ.university.dto.response.UserResponseDto;
import likelion.univ.response.SuccessResponse;
import likelion.univ.university.usecase.GetUnivUsecase;
import likelion.univ.university.usecase.GetUserUsecase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/university")
@Tag(name = "University", description = "대학교 API")
public class UniversityController {

    private final GetUnivUsecase getUnivUsecase;
    private final GetUserUsecase getUserUsecase;


    //-----------대학교 조회 --------//
    @GetMapping("/")
    @Operation(summary = "대학교 조회", description = "대학교를 조회합니다.")
    public SuccessResponse<List<UnivResponseDto>> getAllUniv(){
        List<UnivResponseDto> univList = getUnivUsecase.excute();
        return SuccessResponse.of(univList);
    }

    //-----------유저 조회 --------//
    @GetMapping("/users")
    @Operation(summary = "전체 유저 조회", description = "전체 유저 정보를 조회합니다.")
    public SuccessResponse<List<UserResponseDto>> getAllUser(){
        List<UserResponseDto> userList = getUserUsecase.excute();
        return SuccessResponse.of(userList);
    }
}
