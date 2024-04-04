package likelion.univ.adminUser.usecase;

import likelion.univ.adminUser.dto.response.UserInfoResponseDto;
import likelion.univ.annotation.UseCase;
import likelion.univ.common.response.PageResponse;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import likelion.univ.domain.user.entity.Role;
import likelion.univ.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@UseCase
@RequiredArgsConstructor
public class FindAllByHeadqueatersUsecase {

    private final UserAdaptor userAdaptor;

    public PageResponse<UserInfoResponseDto> execute(Role role, String univName, Pageable pageable) {
        Page<User> users = userAdaptor.findByUnivNameAndRole(role, univName, pageable);
        return PageResponse.of(users.map(UserInfoResponseDto::of));
    }
}
