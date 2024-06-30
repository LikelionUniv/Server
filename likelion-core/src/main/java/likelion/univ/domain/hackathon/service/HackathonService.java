package likelion.univ.domain.hackathon.service;

import likelion.univ.domain.hackathon.entity.Hackathon;
import likelion.univ.domain.hackathon.entity.HackathonForm;
import likelion.univ.domain.hackathon.repository.HackathonFormRepository;
import likelion.univ.domain.hackathon.repository.HackathonRepository;
import likelion.univ.domain.hackathon.repository.condition.HackathonFormSearchCondition;
import likelion.univ.domain.hackathon.response.HackathonFindResponse;
import likelion.univ.domain.hackathon.service.command.HackathonApplyCommand;
import likelion.univ.domain.hackathon.service.command.HackathonModifyCommand;
import likelion.univ.domain.university.entity.University;
import likelion.univ.domain.university.repository.UniversityRepository;
import likelion.univ.domain.user.entity.User;
import likelion.univ.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class HackathonService {

    private final HackathonRepository hackathonRepository;
    private final HackathonFormRepository hackathonFormRepository;
    private final UserRepository userRepository;
    private final UniversityRepository universityRepository;

    public Long apply(HackathonApplyCommand command) {
        User user = userRepository.getById(command.userId());
        University university = universityRepository.getById(command.universityId());
        // TODO: 추후 해커톤 생성 api 개발 시 변경
        Hackathon hackathon = hackathonRepository.getById(1L);
        HackathonForm hackathonForm = command.toHackathonForm(user, university, hackathon);
        hackathonForm.apply();
        return hackathonFormRepository.save(hackathonForm).getId();
    }

    public HackathonFindResponse find(Long userId, Long hackathonFormId) {
        HackathonForm hackathonForm = hackathonFormRepository.getById(hackathonFormId);
        User user = userRepository.getById(userId);
        hackathonForm.validateUser(user);
        return HackathonFindResponse.from(hackathonForm);
    }

    public void modify(HackathonModifyCommand command) {
        HackathonForm hackathonForm = hackathonFormRepository.getById(command.hackathonFormId());
        User user = userRepository.getById(command.userId());
        hackathonForm.validateUser(user);
        hackathonForm.modify(
                command.phone(),
                command.hackathonParts(),
                command.teamName(),
                command.offlineParticipation(),
                command.reasonForNotOffline()
        );
    }

    public Page<HackathonForm> search(HackathonFormSearchCondition condition, Pageable pageable) {
        Page<HackathonForm> result = hackathonFormRepository.search(condition, pageable);
        return result;
    }
}
