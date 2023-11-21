package likelion.univ.adminUser.usecase;

import likelion.univ.adminUser.dto.response.UserInfoResponseDto;
import likelion.univ.annotation.UseCase;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import likelion.univ.domain.user.entity.User;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class FindUserUseCase {

    private final UserAdaptor userAdaptor;

    public UserInfoResponseDto execute(Long userId){
        User user = userAdaptor.findById(userId);
        return UserInfoResponseDto.of(user);

    }
}
