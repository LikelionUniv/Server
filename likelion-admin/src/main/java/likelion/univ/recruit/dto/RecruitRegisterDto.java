package likelion.univ.recruit.dto;

import likelion.univ.domain.recruit.entity.Recruit;
import likelion.univ.domain.university.entity.University;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RecruitRegisterDto {

    private String email;
    private String name;
    private String phoneNumber;

    public Recruit map(University university) {
        return Recruit.builder()
                .email(this.email)
                .name(this.name)
                .phoneNumber(this.getPhoneNumber())
                .university(university)
                .build();
    }
}
