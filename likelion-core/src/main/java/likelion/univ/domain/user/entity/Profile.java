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

    public void updateProfile(String name, String email, Part part){
        this.name=name;
        this.email=email;
        this.part=part;
    }
}
