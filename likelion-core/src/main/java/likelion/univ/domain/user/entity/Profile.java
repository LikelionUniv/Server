package likelion.univ.domain.user.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Objects;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Profile {

    private String name;
    private String email;
    private String profileImage;

    @Column(columnDefinition = "TEXT")
    private String introduction;

    @Enumerated(EnumType.STRING)
    private Part part;


    @Override
    public boolean equals(Object obj) {
        if(this==obj) return true;
        if(obj==null||getClass()!=obj.getClass()) return false;
        Profile profile =(Profile) obj;
        return Objects.equals(getName(),profile.getName())&&
                Objects.equals(getEmail(),profile.getEmail())&&
                Objects.equals(getPart(),profile.getPart())&&
                Objects.equals(getProfileImage(),profile.getProfileImage())&&
                Objects.equals(getIntroduction(),profile.getIntroduction());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(),getEmail(),getPart(),getProfileImage(),getIntroduction());
    }
}
