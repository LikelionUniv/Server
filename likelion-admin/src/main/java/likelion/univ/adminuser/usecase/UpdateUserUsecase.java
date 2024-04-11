package likelion.univ.adminuser.usecase;

import likelion.univ.adminuser.dto.request.UpdateUserRequestDto;
import likelion.univ.adminuser.dto.response.UserInfoResponseDto;
import likelion.univ.annotation.UseCase;
import likelion.univ.domain.user.entity.User;
import likelion.univ.domain.user.repository.UserRepository;
import likelion.univ.domain.user.service.UserDomainService;
import lombok.RequiredArgsConstructor;


@UseCase
@RequiredArgsConstructor
public class UpdateUserUsecase {

    private final UserRepository userRepository;
    private final UserDomainService userDomainService;

    public UserInfoResponseDto execute(Long userId, UpdateUserRequestDto updateUserRequestDto) {
        User user = userRepository.getByIdWithUniversity(userId);
        userDomainService.updateUser(
                user, updateUserRequestDto.getName(),
                updateUserRequestDto.getPart(), updateUserRequestDto.getMajor(),
                updateUserRequestDto.getOrdinal(), updateUserRequestDto.getRole()
        );
        return UserInfoResponseDto.of(user);
    }
}
