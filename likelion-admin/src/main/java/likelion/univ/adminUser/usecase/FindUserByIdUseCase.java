package likelion.univ.adminUser.usecase;

import likelion.univ.adminUser.dto.response.UserInfoResponseDto;
import likelion.univ.annotation.UseCase;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import likelion.univ.domain.user.entity.User;
import lombok.RequiredArgsConstructor;


@UseCase
@RequiredArgsConstructor
public class FindUserByIdUseCase {

    private final UserAdaptor userAdaptor;

    public User findUserById(Long id){
        User user = userAdaptor.findById(id);
        return user;
    }

    public UserInfoResponseDto findById(Long id){
        User user = userAdaptor.findById(id);
        return UserInfoResponseDto.of(user);
    }
}
