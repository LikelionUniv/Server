package likelion.univ.domain.hackathon.service.command;


import likelion.univ.domain.hackathon.entity.HackathonPart;

public record HackathonModifyCommand(
        Long hackathonFormId,
        Long userId,
        String phone,
        HackathonPart hackathonPart,
        String teamName,
        boolean offlineParticipation
) {
}
