package likelion.univ.adminUser.usecase;

import likelion.univ.adminUser.dto.response.UserInfoResponseDto;
import likelion.univ.annotation.UseCase;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import likelion.univ.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@UseCase
@RequiredArgsConstructor
@Slf4j
public class FindUsersByUnivUseCase {

    private final UserAdaptor userAdaptor;

    public List<UserInfoResponseDto> findUsersByUniv(Long id){
        List<User> users = userAdaptor.findUsersByUniversityId(id);
        return users.stream().map(u -> new UserInfoResponseDto(u.getProfile().getName())).collect(Collectors.toList());

    }

}
