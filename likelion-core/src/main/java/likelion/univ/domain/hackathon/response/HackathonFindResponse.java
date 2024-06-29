package likelion.univ.domain.hackathon.response;

import java.util.List;
import likelion.univ.domain.hackathon.entity.HackathonForm;
import likelion.univ.domain.hackathon.entity.HackathonPart;
import likelion.univ.domain.hackathon.entity.HackathonParticipantPart;
import lombok.Builder;

@Builder
public record HackathonFindResponse(
        Long hackathonFormId,
        String name,
        String email,
        String universityName,
        String major,
        String phone,
        List<HackathonPart> hackathonParts,
        String teamName,
        boolean offlineParticipation,
        String reasonForNotOffline
) {

    public static HackathonFindResponse from(HackathonForm hackathonForm) {
        return new HackathonFindResponse(
                hackathonForm.getId(),
                hackathonForm.getName(),
                hackathonForm.getEmail(),
                hackathonForm.getUniversity().getName(),
                hackathonForm.getMajor(),
                hackathonForm.getPhone(),
                hackathonForm.getHackathonParts().stream()
                        .map(HackathonParticipantPart::getHackathonPart)
                        .toList(),
                hackathonForm.getTeamName(),
                hackathonForm.isOfflineParticipation(),
                hackathonForm.getReasonForNotOffline()
        );
    }
}
