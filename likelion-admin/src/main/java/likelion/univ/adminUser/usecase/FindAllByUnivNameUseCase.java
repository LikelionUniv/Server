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
public class FindAllByUnivNameUseCase {

    private final UserAdaptor userAdaptor;

    public List<UserInfoResponseDto> execute(String univName) {
        List<User> users = userAdaptor.findAllByUniversityName(univName);
        return users.stream().map(UserInfoResponseDto::of).collect(Collectors.toList());

    }
}