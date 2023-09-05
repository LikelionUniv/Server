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
public class FindAllByUnivUseCase {

    private final UserAdaptor userAdaptor;

    public List<UserInfoResponseDto> excute(Long univId){
        List<User> users = userAdaptor.findUsersByUniversityId(univId);
        return users.stream().map(u -> new UserInfoResponseDto(u.getId(), u.getProfile().getName(),
                u.getAuthInfo().getEmail(), u.getUniversityInfo().getMajor(),
                u.getProfile().getPart(), u.getUniversityInfo().getOrdinal())).collect(Collectors.toList());
    }
}
