package likelion.univ.auth.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.auth.dto.response.AccountUserInfoDto;
import likelion.univ.domain.user.entity.User;
import likelion.univ.domain.user.repository.UserRepository;
import likelion.univ.utils.AuthenticatedUserUtils;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GetUserInfoUsecase {

    private final AuthenticatedUserUtils authenticatedUserUtils;
    private final UserRepository userRepository;

    public AccountUserInfoDto execute() {
        Long currentUserId = authenticatedUserUtils.getCurrentUserId();
        User user = userRepository.getByIdWithUniversity(currentUserId);
        if (user.getUniversityInfo().getUniversity() == null) {
            return AccountUserInfoDto.of(user, null);
        } else {
            return AccountUserInfoDto.of(user, user.getUniversityInfo().getUniversity().getName());
        }
    }
}
