package likelion.univ.domain.user.entity;

import likelion.univ.domain.university.entity.University;
import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class UniversityInfo {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_id")
    private University university;
    private String major;
    private Long ordinal;

    public void updateUniversityInfo(String major, Long ordinal){
        this.major=major;
        this.ordinal=ordinal;
    }

    public static UniversityInfo universityInfoForSignUp(University university, String major){
        return UniversityInfo.builder()
                .university(university)
                .major(major)
                .build();
    }
}
