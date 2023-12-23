package likelion.univ.alarm.dto;

import likelion.univ.domain.recruit.entity.Recruit;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class GetRecruitsDto {

    private String universityName;
    private List<RecruitDto> recruits;

    public GetRecruitsDto(String universityName, List<Recruit> recruits) {
        this.universityName = universityName;
        this.recruits = recruits.stream()
                .map(recruit -> RecruitDto.from(recruit))
                .toList();
    }

    @Builder
    static class RecruitDto {

        private String name;
        private String phone;
        private String email;

        static RecruitDto from(Recruit recruit) {
            return RecruitDto.builder()
                    .name(recruit.getName())
                    .phone(recruit.getPhoneNumber())
                    .email(recruit.getEmail())
                    .build();
        }
    }
}
