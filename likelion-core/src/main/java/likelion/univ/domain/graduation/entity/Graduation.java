package likelion.univ.domain.graduation.entity;

import likelion.univ.common.entity.BaseTimeEntity;
import likelion.univ.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "ordinal"})})
public class Graduation extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private Long ordinal;

    public Graduation(User user, Long ordinal) {
        this.user = user;
        this.ordinal = ordinal;
    }
}
