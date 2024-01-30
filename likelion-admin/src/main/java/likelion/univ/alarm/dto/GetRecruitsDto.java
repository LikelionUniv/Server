package likelion.univ.alarm.dto;

import likelion.univ.common.processor.DateCustomFormatter;
import likelion.univ.domain.recruit.entity.Recruit;
import lombok.AllArgsConstructor;
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

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    static class RecruitDto {

        private Long id;
        private String name;
        private String phone;
        private String email;
        private String createdDate;

        static RecruitDto from(Recruit recruit) {
            return new RecruitDto(
                    recruit.getId(),
                    recruit.getName(),
                    recruit.getPhoneNumber(),
                    recruit.getEmail(),
                    DateCustomFormatter.format(recruit.getCreatedDate())
            );
        }
    }
}
