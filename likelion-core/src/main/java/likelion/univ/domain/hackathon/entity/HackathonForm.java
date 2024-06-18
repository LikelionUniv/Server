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
import likelion.univ.domain.hackathon.exception.HackathonFormNotEditableException;
import likelion.univ.domain.hackathon.exception.NoAuthorityGuestApplyHackathon;
import likelion.univ.domain.hackathon.exception.NoAuthorityOrdinalApplyHackathon;
import likelion.univ.domain.hackathon.exception.ReasonForNotOfflineException;
import likelion.univ.domain.university.entity.University;
import likelion.univ.domain.user.entity.Role;
import likelion.univ.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class HackathonForm extends BaseTimeEntity {

    public static final Long HACKATHON_ORDINAL = 12L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_id", nullable = false)
    private University university;

    @Column(nullable = false)
    private String major;

    @Column(nullable = false)
    private String phone;

    @Enumerated(STRING)
    @Column(nullable = false)
    private HackathonPart hackathonPart;

    @Column(length = 10, nullable = false)
    private String teamName;

    private boolean offlineParticipation;

    @Column(length = 100)
    private String reasonForNotOffline;

    public HackathonForm(
            User user,
            String name,
            String email,
            University university,
            String major,
            String phone,
            HackathonPart hackathonPart,
            String teamName,
            boolean offlineParticipation,
            String reasonForNotOffline
    ) {
        this.user = user;
        this.name = name;
        this.email = email;
        this.university = university;
        this.major = major;
        this.phone = phone;
        this.hackathonPart = hackathonPart;
        this.teamName = teamName;
        this.offlineParticipation = offlineParticipation;
        this.reasonForNotOffline = reasonForNotOffline;
    }

    public void apply() {
        if (user.getAuthInfo().getRole().equals(Role.GUEST)) {
            throw new NoAuthorityGuestApplyHackathon();
        }
        if (!user.getUniversityInfo().getOrdinal().equals(HACKATHON_ORDINAL)) {
            throw new NoAuthorityOrdinalApplyHackathon();
        }
    }

    public void modify(String phone, HackathonPart hackathonPart, String teamName, boolean offlineParticipation, String reasonForNotOffline) {
        this.phone = phone;
        this.hackathonPart = hackathonPart;
        this.teamName = teamName;
        this.offlineParticipation = offlineParticipation;
        this.reasonForNotOffline = reasonForNotOffline;
    }

    public void validReasonForNotOffline(boolean offlineParticipation, String reasonForNotOffline) {
        if (!offlineParticipation && (reasonForNotOffline == null || reasonForNotOffline.isEmpty()))
            throw new ReasonForNotOfflineException();
    }

    public void validateUser(User user) {
        if (!this.user.equals(user)) {
            throw new HackathonFormNotEditableException();
        }
    }
}

