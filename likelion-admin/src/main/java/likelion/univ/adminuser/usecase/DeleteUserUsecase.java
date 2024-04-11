package likelion.univ.adminuser.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.user.entity.User;
import likelion.univ.domain.user.repository.UserRepository;
import likelion.univ.domain.user.service.UserDomainService;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class DeleteUserUsecase {

    private final UserRepository userRepository;
    private final UserDomainService userDomainService;

    public void execute(Long userId) {
        User user = userRepository.getById(userId);
        userDomainService.deleteUser(user);
    }
}
