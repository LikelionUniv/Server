package likelion.univ.domain.user.entity;

import likelion.univ.common.entity.BaseTimeEntity;
import lombok.*;
import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"email", "loginType"})})
public class User extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Embedded
    private Profile profile;

    @Embedded
    private UniversityInfo universityInfo;

    @Embedded
    private AuthInfo authInfo;

}
