package likelion.univ.adminuser.service;

import java.util.List;
import likelion.univ.adminuser.dto.request.UpdateUserRequestDto;
import likelion.univ.adminuser.dto.response.UserInfoResponseDto;
import likelion.univ.common.response.PageResponse;
import likelion.univ.domain.user.entity.User;
import likelion.univ.domain.user.repository.UserRepository;
import likelion.univ.domain.user.service.UserService;
import likelion.univ.utils.AuthenticatedUserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AdminUserService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final AuthenticatedUserUtils authenticatedUserUtils;

    @Transactional
    public void delete(Long userId) {
        User user = userRepository.getById(userId);
        userService.deleteUser(user);
    }

    @Transactional
    public void deleteAllUser(List<Long> userIds) {
        List<User> users = userRepository.findAllByIdsExactly(userIds);
        userService.deleteAll(users);
    }

    public PageResponse<UserInfoResponseDto> findAllByAdminUserUniv(Pageable pageable) {
        User user = authenticatedUserUtils.getCurrentUser();
        Page<User> users = userRepository.findByUniversityInfoUniversityId(
                user.getUniversityInfo().getUniversity().getId(),
                pageable
        );
        return PageResponse.of(users.map(UserInfoResponseDto::of));
    }

    @Transactional
    public UserInfoResponseDto updateUser(Long userId, UpdateUserRequestDto updateUserRequestDto) {
        User user = userRepository.getByIdWithUniversity(userId);
        userService.updateUser(
                user, updateUserRequestDto.getName(),
                updateUserRequestDto.getPart(), updateUserRequestDto.getMajor(),
                updateUserRequestDto.getOrdinal(), updateUserRequestDto.getRole()
        );
        return UserInfoResponseDto.of(user);
    }
}
