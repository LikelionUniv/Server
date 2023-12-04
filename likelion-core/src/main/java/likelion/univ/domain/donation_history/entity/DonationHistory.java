package likelion.univ.domain.donation_history.entity;

import likelion.univ.common.entity.BaseTimeEntity;
import likelion.univ.domain.project.entity.ProjectMember;
import likelion.univ.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DonationHistory extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    private String title;

    @Column(columnDefinition="TEXT")
    private String body;

    private Long viewCount = Long.valueOf(0);

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="project_id", insertable = false, updatable = false)
    private List<DonationHistoryAttachment> attachments = new ArrayList<>();

    public void viewCountUp(){
        this.viewCount += 1;
    }
}