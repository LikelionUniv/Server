package likelion.univ.domain.hackathon.service.command;


import java.util.Set;
import likelion.univ.domain.hackathon.entity.Hackathon;
import likelion.univ.domain.hackathon.entity.HackathonForm;
import likelion.univ.domain.hackathon.entity.HackathonPart;
import likelion.univ.domain.university.entity.University;
import likelion.univ.domain.user.entity.User;

public record HackathonApplyCommand(
        Long userId,
        String name,
        String email,
        Long universityId,
        String major,
        String phone,
        Set<HackathonPart> hackathonParts,
        String teamName,
        boolean offlineParticipation,
        String reasonForNotOffline
) {
    public HackathonForm toHackathonForm(User user, University university, Hackathon hackathon) {
        return new HackathonForm(
                user,
                name,
                email,
                university,
                hackathon,
                major,
                phone,
                hackathonParts,
                teamName,
                offlineParticipation,
                reasonForNotOffline
        );
    }
}
