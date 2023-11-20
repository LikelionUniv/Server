package likelion.univ.domain.user.entity;

import likelion.univ.common.entity.BaseTimeEntity;
import lombok.*;
import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Entity
<<<<<<< HEAD
@Table(name="users", uniqueConstraints = {@UniqueConstraint(columnNames = {"email", "loginType"})})
=======
@Table(name="user", uniqueConstraints = {@UniqueConstraint(columnNames = {"email", "loginType"})})
>>>>>>> 51497509e432a26e57f31debfb42a2364d4d2484
public class User extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Profile profile;

    @Embedded
    private UniversityInfo universityInfo;

    @Embedded
    private AuthInfo authInfo;

    public void editProfile(Profile profile){
        this.profile = profile;
    }
}
