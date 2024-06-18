package likelion.univ.domain.hackathon.response;

import likelion.univ.domain.hackathon.entity.HackathonPart;

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
}
