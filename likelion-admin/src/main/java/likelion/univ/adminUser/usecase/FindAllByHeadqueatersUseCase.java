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
public class FindAllByHeadqueatersUseCase {

    private final UserAdaptor userAdaptor;

    public PageResponse execute(Pageable pageable, String role, String univName){
        Page<User> users = userAdaptor.findAll(pageable);
        if (role != null && univName == null) {
            users = userAdaptor.findByRole(Role.valueOf(role), pageable);
        }else if (role == null && univName != null) {
            users = userAdaptor.findByUnivName(univName, pageable);
        }else if (role != null && univName != null){
            users = userAdaptor.findByUnivNameAndRole(Role.valueOf(role), univName, pageable);
        }

        return PageResponse.of(users.map(u-> UserInfoResponseDto.of(u)));
    }

}
