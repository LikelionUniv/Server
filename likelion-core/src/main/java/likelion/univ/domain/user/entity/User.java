package likelion.univ.domain.user.entity;

import likelion.univ.common.entity.BaseTimeEntity;
import lombok.*;
import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Entity
@Table(name="user", uniqueConstraints = {@UniqueConstraint(columnNames = {"email", "loginType"})})
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
