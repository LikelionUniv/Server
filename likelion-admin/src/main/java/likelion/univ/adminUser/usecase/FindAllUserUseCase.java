package likelion.univ.adminUser.usecase;

import likelion.univ.adminUser.dto.response.UserInfoResponseDto;
import likelion.univ.annotation.UseCase;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import likelion.univ.domain.user.entity.User;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@UseCase
@RequiredArgsConstructor
public class FindAllUserUseCase {
    private final UserAdaptor userAdaptor;

    public List<UserInfoResponseDto> findAllUser(){
        List<User> users = userAdaptor.findAllUser();
        return users.stream().map(u -> new UserInfoResponseDto(u.getProfile().getName())).collect(Collectors.toList());

    }
}
