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
public class UpdateUserUsecase {

    private final UserAdaptor userAdaptor;
    private final UserDomainService userDomainService;

    public UserInfoResponseDto execute(Long userId, UpdateUserRequestDto updateUserRequestDto) {
        User user = userAdaptor.findByIdWithUniversity(userId);
        userDomainService.updateUser(
                user, updateUserRequestDto.getName(),
                updateUserRequestDto.getPart(), updateUserRequestDto.getMajor(),
                updateUserRequestDto.getOrdinal(), updateUserRequestDto.getRole()
        );
        return UserInfoResponseDto.of(user);
    }
}
