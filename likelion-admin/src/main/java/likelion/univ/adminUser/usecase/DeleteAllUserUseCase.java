package likelion.univ.adminUser.usecase;

import likelion.univ.adminUser.dto.request.DeletedUserListRequestDto;
import likelion.univ.adminUser.dto.response.UserInfoResponseDto;
import likelion.univ.annotation.UseCase;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import likelion.univ.domain.user.entity.User;
import likelion.univ.domain.user.service.UserDomainService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class DeleteAllUserUseCase {

    private final UserAdaptor userAdaptor;
    private final UserDomainService userDomainService;

    public void execute(DeletedUserListRequestDto deletedUserListRequestDto){
        List<User> users = userAdaptor.findAllByIdIn(deletedUserListRequestDto.getIds());
        userDomainService.deleteAll(users);
    }
}