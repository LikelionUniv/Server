package likelion.univ.hackathon;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import likelion.univ.domain.hackathon.service.HackathonService;
import likelion.univ.email.sender.EmailSender;
import likelion.univ.hackathon.request.HackathonApplyRequest;
import likelion.univ.hackathon.request.HackathonModifyRequest;
import likelion.univ.response.SuccessResponse;
import likelion.univ.utils.AuthenticatedUserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/hackathons")
@Tag(name = "해커톤", description = "해커톤 API")
public class HackathonController {

    private final AuthenticatedUserUtils userUtils;
    private final HackathonService hackathonService;
    private final EmailSender emailSender;

    @Operation(summary = "해커톤 신청")
    @PostMapping
    public SuccessResponse<Long> apply(
            @Valid @RequestBody HackathonApplyRequest request
    ) {
        Long userId = userUtils.getCurrentUserId();
        Long projectId = hackathonService.apply(request.toCommand(userId));
        // TODO: emailSender.send(); ???
        return SuccessResponse.of(projectId);
    }

    @Operation(summary = "해커톤 신청 수정")
    @PutMapping("{hackathonFormId}")
    public SuccessResponse<Long> modify(
            @Valid @RequestBody HackathonModifyRequest request,
            @PathVariable("hackathonFormId") Long hackathonFormId
    ) {
        Long userId = userUtils.getCurrentUserId();
        hackathonService.modify(request.toCommand(userId, hackathonFormId));
        return SuccessResponse.of(null);
    }

}
