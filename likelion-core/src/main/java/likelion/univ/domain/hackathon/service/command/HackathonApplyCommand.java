package likelion.univ.domain.hackathon.service.command;


import likelion.univ.domain.hackathon.entity.HackathonForm;
import likelion.univ.domain.hackathon.entity.HackathonPart;
import likelion.univ.domain.university.entity.University;
import likelion.univ.domain.user.entity.User;

public record HackathonApplyCommand(
        Long userId,
        String name,
        String email,
        String universityName,
        String major,
        String phone,
        HackathonPart hackathonPart,
        String teamName
) {
    public HackathonForm toHackathonForm(User user, University university) {
        return new HackathonForm(
                user,
                name,
                email,
                university,
                major,
                phone,
                hackathonPart,
                teamName
        );
    }
}
