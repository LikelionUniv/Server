package likelion.univ.domain.hackathon.response;

import java.time.LocalDate;
import likelion.univ.domain.hackathon.entity.Hackathon;
import likelion.univ.domain.hackathon.entity.HackathonForm;
import lombok.Builder;

@Builder
public record HackathonFindResponse(
        Long hackathonId,
        Long hackathonFormId,
        String hackathonName,
        LocalDate startDate,
        LocalDate endDate) {

    public static HackathonFindResponse from(HackathonForm hackathonForm) {
        final Hackathon hackathon = hackathonForm.getHackathon();
        return new HackathonFindResponse(
                hackathon.getId(),
                hackathonForm.getId(),
                hackathon.getName(),
                hackathon.getStartDate(),
                hackathon.getEndDate()
        );
    }
}
