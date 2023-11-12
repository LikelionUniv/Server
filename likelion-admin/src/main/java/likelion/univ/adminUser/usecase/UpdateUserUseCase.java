package likelion.univ.adminUser.usecase;

import likelion.univ.adminUser.dto.request.UpdateUserRequestDto;
import likelion.univ.adminUser.dto.response.UserInfoResponseDto;
import likelion.univ.annotation.UseCase;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import likelion.univ.domain.user.entity.User;
import likelion.univ.domain.user.service.UserDomainService;
import lombok.RequiredArgsConstructor;


@UseCase
@RequiredArgsConstructor
public class UpdateUserUseCase {

    private final UserAdaptor userAdaptor;
    private final UserDomainService userDomainService;


    public UserInfoResponseDto excute(Long userId, UpdateUserRequestDto updateUserRequestDto){
        User user = userAdaptor.findById(userId);
        userDomainService.updateUser(user, updateUserRequestDto.getName(),
        updateUserRequestDto.getPart(),updateUserRequestDto.getMajor(),
        updateUserRequestDto.getOrdinal());
        return UserInfoResponseDto.of(user);
    }
}
