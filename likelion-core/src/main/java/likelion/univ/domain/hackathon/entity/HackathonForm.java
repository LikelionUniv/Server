package likelion.univ.domain.hackathon.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import likelion.univ.common.entity.BaseTimeEntity;
import likelion.univ.domain.hackathon.exception.HackathonFormNotEditableException;
import likelion.univ.domain.hackathon.exception.NoAuthorityGuestApplyHackathon;
import likelion.univ.domain.hackathon.exception.NoAuthorityOrdinalApplyHackathon;
import likelion.univ.domain.hackathon.exception.ReasonForNotOfflineException;
import likelion.univ.domain.university.entity.University;
import likelion.univ.domain.user.entity.Role;
import likelion.univ.domain.user.entity.User;
import lombok.AccessLevel;
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

    @OneToMany(mappedBy = "hackathonForm", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<HackathonParticipantPart> hackathonParts = new HashSet<>();

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
            Set<HackathonPart> hackathonParts,
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
        setHackathonParts(hackathonParts);
        this.teamName = teamName;
        this.offlineParticipation = offlineParticipation;
        setReasonForNotOffline(offlineParticipation, reasonForNotOffline);
    }

    private void setHackathonParts(Set<HackathonPart> hackathonParts) {
        this.hackathonParts.clear();
        this.hackathonParts.addAll(hackathonParts.stream()
                .map(it -> new HackathonParticipantPart(this, it))
                .collect(Collectors.toSet()));
    }

    private void setReasonForNotOffline(boolean offlineParticipation, String reasonForNotOffline) {
        if (offlineParticipation) {
            this.reasonForNotOffline = null;
        } else {
            validReasonForNotOffline(reasonForNotOffline);
            this.reasonForNotOffline = reasonForNotOffline;
        }
    }

    private void validReasonForNotOffline(String reasonForNotOffline) {
        if ((reasonForNotOffline == null || reasonForNotOffline.isEmpty())) {
            throw new ReasonForNotOfflineException();
        }
    }

    public void apply() {
        if (user.getAuthInfo().getRole().equals(Role.GUEST)) {
            throw new NoAuthorityGuestApplyHackathon();
        }
        if (!user.getUniversityInfo().getOrdinal().equals(HACKATHON_ORDINAL)) {
            throw new NoAuthorityOrdinalApplyHackathon();
        }

        setReasonForNotOffline(offlineParticipation, reasonForNotOffline);
    }

    public void modify(
            String phone,
            Set<HackathonPart> hackathonParts,
            String teamName,
            boolean offlineParticipation,
            String reasonForNotOffline
    ) {
        this.phone = phone;
        setHackathonParts(hackathonParts);
        this.teamName = teamName;
        this.offlineParticipation = offlineParticipation;
        setReasonForNotOffline(offlineParticipation, reasonForNotOffline);
    }

    public void validateUser(User user) {
        if (!this.user.equals(user)) {
            throw new HackathonFormNotEditableException();
        }
    }
}

