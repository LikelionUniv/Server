package likelion.univ.adminUser.usecase;

import likelion.univ.adminUser.dto.response.UserInfoResponseDto;
import likelion.univ.annotation.UseCase;
import likelion.univ.common.response.PageResponse;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import likelion.univ.domain.user.entity.User;
import likelion.univ.utils.AuthenticatedUserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@UseCase
@RequiredArgsConstructor
public class FindAllByUnivAdminUseCase {

    private final UserAdaptor userAdaptor;
    private final AuthenticatedUserUtils authenticatedUserUtils;

    public PageResponse execute(Pageable pageable) {
        User user = authenticatedUserUtils.getCurrentUser();
        Page<User> users = userAdaptor.findByUniversityInfoUniversityId(
                user.getUniversityInfo().getUniversity().getId(),
                pageable
        );
        return PageResponse.of(users.map(UserInfoResponseDto::of));
    }
}
