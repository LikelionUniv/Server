package likelion.univ.domain.hackathon.response;

import likelion.univ.domain.hackathon.entity.HackathonForm;
import likelion.univ.domain.hackathon.entity.HackathonPart;
import lombok.Builder;

@Builder
public record HackathonFindResponse(
        Long hackathonFormId,
        String name,
        String email,
        String universityName,
        String major,
        String phone,
        HackathonPart hackathonPart,
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
                hackathonForm.getHackathonPart(),
                hackathonForm.getTeamName(),
                hackathonForm.isOfflineParticipation(),
                hackathonForm.getReasonForNotOffline()
        );
    }
}
