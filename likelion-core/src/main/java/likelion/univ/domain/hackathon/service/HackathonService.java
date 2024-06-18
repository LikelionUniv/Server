package likelion.univ.domain.hackathon.service;

import likelion.univ.domain.hackathon.entity.HackathonForm;
import likelion.univ.domain.hackathon.repository.HackathonFormRepository;
import likelion.univ.domain.hackathon.response.HackathonFindResponse;
import likelion.univ.domain.hackathon.service.command.HackathonApplyCommand;
import likelion.univ.domain.hackathon.service.command.HackathonModifyCommand;
import likelion.univ.domain.university.entity.University;
import likelion.univ.domain.university.repository.UniversityRepository;
import likelion.univ.domain.user.entity.User;
import likelion.univ.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class HackathonService {

    private final HackathonFormRepository hackathonFormRepository;
    private final UserRepository userRepository;
    private final UniversityRepository universityRepository;

    public Long apply(HackathonApplyCommand command) {
        User user = userRepository.getById(command.userId());
        University university = universityRepository.getByName(command.universityName());
        HackathonForm hackathonForm = command.toHackathonForm(user, university);
        hackathonForm.apply();
        return hackathonFormRepository.save(hackathonForm).getId();
    }

    public HackathonFindResponse find(Long userId, Long hackathonFormId) {
        HackathonForm hackathonForm = hackathonFormRepository.getById(hackathonFormId);
        User user = userRepository.getById(userId);
        hackathonForm.validateUser(user);
        return new HackathonFindResponse(
                hackathonForm.getId(),
                hackathonForm.getName(),
                hackathonForm.getEmail(),
                hackathonForm.getUniversity().getName(),
                hackathonForm.getMajor(),
                hackathonForm.getPhone(),
                hackathonForm.getHackathonPart(),
                hackathonForm.getTeamName(),
                hackathonForm.isOfflineParticipation(),
                hackathonForm.getReasonForNotOffline()
        );
    }

    public void modify(HackathonModifyCommand command) {
        HackathonForm hackathonForm = hackathonFormRepository.getById(command.hackathonFormId());
        User user = userRepository.getById(command.userId());
        hackathonForm.validateUser(user);
        hackathonForm.modify(command.phone(),
                command.hackathonPart(),
                command.teamName(),
                command.offlineParticipation(),
                command.reasonForNotOffline()
        );
    }
}
