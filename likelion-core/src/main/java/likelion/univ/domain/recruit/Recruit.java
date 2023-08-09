package likelion.univ.domain.recruit;

import likelion.univ.common.entity.BaseTimeEntity;
import likelion.univ.domain.university.entity.University;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Recruit extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recruit_id")
    private Long id;

    private String name;

    private String email;

    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_id", nullable = false)
    private University university;

    @Builder
    public Recruit(String name, String email, String phoneNumber, University university) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.university = university;
    }
}
