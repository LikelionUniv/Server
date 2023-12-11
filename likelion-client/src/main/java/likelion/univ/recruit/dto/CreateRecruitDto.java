package likelion.univ.recruit.dto;

import likelion.univ.domain.recruit.entity.Recruit;
import likelion.univ.domain.university.entity.University;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CreateRecruitDto {

    private String name;
    private String email;
    private String phone;
    private int generation;

    public Recruit toEntity(University university) {
        return Recruit.builder()
                .email(this.email)
                .name(this.name)
                .phoneNumber(this.phone)
                .generation(this.generation)
                .university(university)
                .build();
    }
}
