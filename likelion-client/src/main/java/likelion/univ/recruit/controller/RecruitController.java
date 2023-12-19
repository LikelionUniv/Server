package likelion.univ.recruit.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import likelion.univ.recruit.dto.CreateRecruitDto;
import likelion.univ.recruit.usecase.CreateRecruitUsecase;
import likelion.univ.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/recruit")
@Tag(name = "Recruit", description = "리크루팅 알람 등록 API")
public class RecruitController {

    private final CreateRecruitUsecase createRecruitUsecase;

    @PostMapping("/{universityName}")
    @Operation(summary = "리크루팅 등록", description = "리크루팅 알람에 등록하는 API 입니다.")
    public SuccessResponse register(@RequestBody CreateRecruitDto createRecruitDto,
                                    @PathVariable String universityName) {

        Long recruitId = createRecruitUsecase.execute(createRecruitDto, universityName);
        return SuccessResponse.of(recruitId);
    }
}
