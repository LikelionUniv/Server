package likelion.univ.hackathon.request;


import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Set;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import likelion.univ.domain.hackathon.entity.HackathonPart;
import likelion.univ.domain.hackathon.service.command.HackathonModifyCommand;

public record HackathonModifyRequest(
        @NotBlank
        @Schema(description = "신청자 전화번호 (-생략, 숫자만)", example = "01012341234")
        String phone,

        @NotEmpty
        @Schema(description = "해커톤 파트들", example = "PM,BACKEND")
        Set<HackathonPart> hackathonParts,

        @NotBlank
        @Size(max = 10, message = "팀명은 10자 이하여야 합니다.")
        @Schema(description = "팀명", example = "멋쟁이팀")
        String teamName,

        @Schema(description = "오프라인 참가 여부", example = "true")
        boolean offlineParticipation,

        @Size(max = 100, message = "불참사유는 100자 이내여야 합니다.")
        @Schema(description = "불참 사유", example = "개인 사정으로 인해 불참합니다.")
        String reasonForNotOffline
) {
    public HackathonModifyCommand toCommand(Long userId, Long hackathonFormId) {
        return new HackathonModifyCommand(
                hackathonFormId,
                userId,
                phone,
                hackathonParts,
                teamName,
                offlineParticipation,
                reasonForNotOffline
        );
    }
}
