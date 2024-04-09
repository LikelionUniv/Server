package likelion.univ.adminuser.service;

import likelion.univ.adminuser.dto.response.UserInfoResponseDto;
import likelion.univ.common.response.PageResponse;
import likelion.univ.domain.user.entity.Role;
import likelion.univ.domain.user.entity.User;
import likelion.univ.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class HeadquarterUserService {

    private final UserRepository userRepository;

    public PageResponse<UserInfoResponseDto> findAll(Role role, String univName, Pageable pageable) {
        Page<User> users = userRepository.findByUnivNameAndRole(role, univName, pageable);
        return PageResponse.of(users.map(UserInfoResponseDto::of));
    }
}
