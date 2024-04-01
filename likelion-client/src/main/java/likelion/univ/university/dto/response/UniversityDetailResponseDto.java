package likelion.univ.university.dto.response;

import likelion.univ.domain.university.entity.University;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UniversityDetailResponseDto {
    private String universityName;
    private String location;
    private String image;
    private String recruitUrl;

    public static UniversityDetailResponseDto of(University university) {
        return UniversityDetailResponseDto.builder()
                .universityName(university.getName())
                .location(university.getLocation())
                .image(university.getImage())
                .recruitUrl(university.getRecruitUrl())
                .build();
    }

}
