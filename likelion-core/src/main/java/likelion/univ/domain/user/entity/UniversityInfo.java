package likelion.univ.domain.user.entity;

import likelion.univ.domain.university.entity.University;
import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Objects;

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



    @Override
    public boolean equals(Object obj) {
        if(this==obj) return true;
        if(obj==null||getClass()!=obj.getClass()) return false;
        UniversityInfo universityInfo =(UniversityInfo) obj;
        return Objects.equals(getUniversity(),universityInfo.getUniversity())&&
                Objects.equals(getMajor(),universityInfo.getMajor())&&
                Objects.equals(getOrdinal(),universityInfo.getOrdinal());

    }

    @Override
    public int hashCode() {
        return Objects.hash(getUniversity(),getMajor(),getOrdinal());
    }
}
