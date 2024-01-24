package likelion.univ.domain.university.entity;

import likelion.univ.common.entity.BaseTimeEntity;
import likelion.univ.domain.user.entity.Part;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.bytebuddy.dynamic.TypeResolutionStrategy;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class University extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private String location;
    private String image;
    private String recruiteUrl;
    @Enumerated(EnumType.STRING)
    private UniversityStatus universityStatus = UniversityStatus.ACTIVE;

}
