package likelion.univ.adminuser.usecase;

import likelion.univ.adminuser.dto.response.UserInfoResponseDto;
import likelion.univ.annotation.UseCase;
import likelion.univ.common.response.PageResponse;
import likelion.univ.domain.user.entity.User;
import likelion.univ.domain.user.repository.UserRepository;
import likelion.univ.utils.AuthenticatedUserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@UseCase
@RequiredArgsConstructor
public class FindAllByUnivAdminUsecase {

    private final UserRepository userRepository;
    private final AuthenticatedUserUtils authenticatedUserUtils;

    public PageResponse execute(Pageable pageable) {
        User user = authenticatedUserUtils.getCurrentUser();
        Page<User> users = userRepository.findByUniversityInfoUniversityId(
                user.getUniversityInfo().getUniversity().getId(),
                pageable
        );
        return PageResponse.of(users.map(UserInfoResponseDto::of));
    }
}
