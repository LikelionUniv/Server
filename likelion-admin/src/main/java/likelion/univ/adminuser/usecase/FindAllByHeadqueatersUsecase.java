package likelion.univ.adminuser.usecase;

import likelion.univ.adminuser.dto.response.UserInfoResponseDto;
import likelion.univ.annotation.UseCase;
import likelion.univ.common.response.PageResponse;
import likelion.univ.domain.user.entity.Role;
import likelion.univ.domain.user.entity.User;
import likelion.univ.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@UseCase
@RequiredArgsConstructor
public class FindAllByHeadqueatersUsecase {

    private final UserRepository userRepository;

    public PageResponse<UserInfoResponseDto> execute(Role role, String univName, Pageable pageable) {
        Page<User> users = userRepository.findByUnivNameAndRole(role, univName, pageable);
        return PageResponse.of(users.map(UserInfoResponseDto::of));
    }
}
