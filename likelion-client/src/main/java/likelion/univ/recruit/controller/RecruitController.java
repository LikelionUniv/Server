package likelion.univ.recruit.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import likelion.univ.recruit.dto.CreateRecruitDto;
import likelion.univ.recruit.service.ClientRecruitService;
import likelion.univ.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/recruit")
@Tag(name = "Recruit", description = "리크루팅 알람 등록 API")
public class RecruitController {

    private final ClientRecruitService clientRecruitService;

    @Operation(summary = "리크루팅 등록", description = "리크루팅 알람에 등록하는 API 입니다.")
    @PostMapping("/{universityName}")
    public SuccessResponse register(
            @RequestBody CreateRecruitDto createRecruitDto,
            @PathVariable("universityName") String universityName
    ) {
        Long recruitId = clientRecruitService.create(createRecruitDto, universityName);
        return SuccessResponse.of(recruitId);
    }
}
