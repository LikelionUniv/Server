package likelion.univ.hackathon.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import likelion.univ.common.response.PageResponse;
import likelion.univ.domain.hackathon.entity.HackathonForm;
import likelion.univ.domain.hackathon.response.HackathonFindResponse;
import likelion.univ.domain.hackathon.service.HackathonService;
import likelion.univ.hackathon.dto.request.HackathonFormSearchRequest;
import likelion.univ.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/hackathons")
@Tag(name = "해커톤", description = "어드민 해커톤 API")
public class HackathonController {

    private final HackathonService hackathonService;

    @Operation(summary = "해커톤 신청 조회")
    @GetMapping
    public SuccessResponse<PageResponse<HackathonFindResponse>> searchHackathons(
            @ParameterObject @Valid HackathonFormSearchRequest request,
            @ParameterObject Pageable pageable
    ) {
        Page<HackathonForm> result = hackathonService.search(request.toCondition(), pageable);
        return SuccessResponse.of(PageResponse.of(result.map(HackathonFindResponse::from)));
    }
}
