package likelion.univ.university.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import likelion.univ.response.SuccessResponse;
import likelion.univ.university.dto.response.UnivResponseDto;
import likelion.univ.university.dto.response.UniversityDetailResponseDto;
import likelion.univ.university.usecase.GetLocationUnivDetailsUsecase;
import likelion.univ.university.usecase.GetTotalUnivDetailsUsecase;
import likelion.univ.university.usecase.GetUnivUsecase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/university")
@Tag(name = "University", description = "대학교 API")
public class UniversityController {
    private final GetUnivUsecase getUnivUsecase;
    private final GetTotalUnivDetailsUsecase getTotalUnivDetailsUsecase;
    private final GetLocationUnivDetailsUsecase getLocationUnivDetailsUsecase;

    //-----------대학교 조회 --------//
    @GetMapping("/")
    @Operation(summary = "대학교 조회", description = "대학교를 조회합니다.")
    public SuccessResponse<Object> getAllUniv() {
        List<UnivResponseDto> univList = getUnivUsecase.execute();
        return SuccessResponse.of(univList);
    }

    @GetMapping("/all")
    @Operation(summary = "대학교 전체 조회", description = "대학교 잔체를 조회합니다.")
    public SuccessResponse<List<UniversityDetailResponseDto>> getAllUnivList() {
        List<UniversityDetailResponseDto> result = getTotalUnivDetailsUsecase.execute();
        return SuccessResponse.of(result);
    }

    @GetMapping("/{location}")
    @Operation(summary = "지역별 대학교 조회", description = "대학교를 지역별로 조회합니다.")
    public SuccessResponse<List<UniversityDetailResponseDto>> getLocalUnivList(
            @PathVariable("location") String location
    ) {
        List<UniversityDetailResponseDto> result = getLocationUnivDetailsUsecase.execute(location);
        return SuccessResponse.of(result);
    }
}
