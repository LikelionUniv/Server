package likelion.univ.adminuser.service;

import likelion.univ.adminuser.dto.response.UserInfoResponseDto;
import likelion.univ.common.response.PageResponse;
import likelion.univ.domain.graduation.entity.Graduation;
import likelion.univ.domain.graduation.repository.GraduationRepository;
import likelion.univ.domain.graduation.service.GraduationService;
import likelion.univ.domain.graduation.service.command.GraduationsCreateCommand;
import likelion.univ.domain.user.entity.Role;
import likelion.univ.domain.user.entity.User;
import likelion.univ.domain.user.repository.UserRepository;
import likelion.univ.utils.AuthenticatedUserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class HeadquarterUserService {
    private final AuthenticatedUserUtils userUtils;
    private final UserRepository userRepository;
    private final GraduationRepository graduationRepository;
    private final GraduationService graduationService;

    public PageResponse<UserInfoResponseDto> findAll(Role role, String univName, Pageable pageable) {
        Page<User> users = userRepository.findByUnivNameAndRole(role, univName, pageable);
        return PageResponse.of(users.map(UserInfoResponseDto::of));
    }

    @Transactional
    public void graduateUsers(List<Long> userIds, Long ordinal) {
        userUtils.validateSuperAdmin();

        List<Graduation> existingGraduations = graduationRepository.findAllByOrdinalAndUserIdIn(ordinal, userIds);
        List<Long> existingUserIds = existingGraduations.stream().map(it -> it.getUser().getId()).toList();
        userIds.removeAll(existingUserIds);

        graduationService.createGraduation(new GraduationsCreateCommand(userIds, ordinal));
    }
}
