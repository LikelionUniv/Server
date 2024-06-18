package likelion.univ.domain.hackathon.response;

import likelion.univ.domain.hackathon.entity.HackathonPart;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HackathonFindResponse {
    private Long hackathonFormId;
    private String name;
    private String email;
    private String universityName;
    private String major;
    private String phone;
    private HackathonPart hackathonPart;
    private String teamName;
    private boolean offlineParticipation;
    private String reasonForNotOffline;
}