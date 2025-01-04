package likelion.univ.domain.graduation.service;

import likelion.univ.domain.graduation.entity.Graduation;
import likelion.univ.domain.graduation.exception.GraduationNotFoundException;
import likelion.univ.domain.graduation.repository.GraduationRepository;
import likelion.univ.domain.user.entity.User;
import likelion.univ.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class GraduationService {
    private final GraduationRepository graduationRepository;
    private final UserRepository userRepository;

    public void createGraduation(GraduationsCreateCommand command) {
        List<User> users = userRepository.findAllByIdIn(command.userId());
        List<Graduation> graduations = users.stream().map(command::toGraduations).toList();
        graduationRepository.saveAll(graduations);
    }

    public String issue(Long userId, Long ordinal) {
        Graduation graduation = graduationRepository.findTopByUserIdAndOrdinal(userId, ordinal)
                .orElseThrow(GraduationNotFoundException::new);

        // Todo: 수료증 pdf 생성 후 s3 저장 -> url 변환 로직 추가
        // 임시 return
        return "https://likelion-univ-dev.s3.ap-northeast-2.amazonaws.com/test.jpeg";
    }
}
