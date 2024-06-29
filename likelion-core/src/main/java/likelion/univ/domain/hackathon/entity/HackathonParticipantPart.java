package likelion.univ.domain.hackathon.entity;

import static javax.persistence.EnumType.STRING;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import likelion.univ.common.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class HackathonParticipantPart extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hackathon_form_id")
    private HackathonForm hackathonForm;

    @Enumerated(STRING)
    @Column(nullable = false)
    private HackathonPart hackathonPart;

    public HackathonParticipantPart(HackathonForm hackathonForm, HackathonPart hackathonPart) {
        this.hackathonForm = hackathonForm;
        this.hackathonPart = hackathonPart;
    }
}
