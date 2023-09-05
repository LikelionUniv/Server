package likelion.univ.domain.user.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Profile {

    private String name;
    // 010-0000-0000 형태로
    private String phoneNumber;
    private String profileImage;

    @Column(columnDefinition = "TEXT")
    private String introduction;

    @Enumerated(EnumType.STRING)
    private Part part;

    public void updateProfile(String name, Part part){
        this.name=name;
        this.part=part;
    }

    public static Profile profileForSignUp(String name, String profileImage){
        return Profile.builder()
                .name(name)
                .profileImage(profileImage)
                .build();
    }
}
