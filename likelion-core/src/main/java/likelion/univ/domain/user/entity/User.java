package likelion.univ.domain.user.entity;

import likelion.univ.common.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "user")
public class User /*extends BaseTimeEntity*/{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    private LocalDateTime lastLoginTime;

    @Embedded
    private Profile profile;

    @Embedded
    private UniversityInfo universityInfo;

    @Embedded
    private AuthInfo authInfo;




}
