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
<<<<<<< HEAD
    // 010-0000-0000 형태로
    private String phoneNumber;
=======
>>>>>>> 51497509e432a26e57f31debfb42a2364d4d2484
    private String profileImage;

    @Column(columnDefinition = "TEXT")
    private String introduction;

    @Enumerated(EnumType.STRING)
    private Part part;

<<<<<<< HEAD

    public void updateProfile(String name, Part part) {
        this.name = name;
        this.part = part;
    }

=======
>>>>>>> 51497509e432a26e57f31debfb42a2364d4d2484
    public static Profile fromName(String name){
        return Profile.builder()
                .name(name)
                .build();
    }
}