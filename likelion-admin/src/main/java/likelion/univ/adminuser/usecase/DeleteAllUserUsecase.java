package likelion.univ.adminuser.usecase;

import java.util.List;
import likelion.univ.adminuser.dto.request.DeletedUserListRequestDto;
import likelion.univ.annotation.UseCase;
import likelion.univ.domain.user.entity.User;
import likelion.univ.domain.user.repository.UserRepository;
import likelion.univ.domain.user.service.UserDomainService;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class DeleteAllUserUsecase {

    private final UserRepository userRepository;
    private final UserDomainService userDomainService;

    public void execute(DeletedUserListRequestDto deletedUserListRequestDto) {
        List<User> users = userRepository.findAllByIdsExactly(deletedUserListRequestDto.getIds());
        userDomainService.deleteAll(users);
    }
}
