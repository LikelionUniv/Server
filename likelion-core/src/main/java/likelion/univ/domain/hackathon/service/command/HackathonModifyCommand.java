package likelion.univ.domain.hackathon.service.command;


import java.util.Set;
import likelion.univ.domain.hackathon.entity.HackathonPart;

public record HackathonModifyCommand(
        Long hackathonFormId,
        Long userId,
        String phone,
        Set<HackathonPart> hackathonParts,
        String teamName,
        boolean offlineParticipation,
        String reasonForNotOffline
) {
}
