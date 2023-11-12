package likelion.univ.adminUser.usecase;

import likelion.univ.adminUser.dto.response.UserInfoResponseDto;
import likelion.univ.annotation.UseCase;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import likelion.univ.domain.user.entity.User;
import likelion.univ.utils.AuthentiatedUserUtils;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@UseCase
@RequiredArgsConstructor
public class FindAllByUnivAdminUseCase {

    private final UserAdaptor userAdaptor;
    private final AuthentiatedUserUtils authentiatedUserUtils;

    public List<UserInfoResponseDto> excute() {
        User user = authentiatedUserUtils.getCurrentUser();

        List<User> users = userAdaptor.findUsersByUniversityId(user.getUniversityInfo().getUniversity().getId());
        return users.stream().map(UserInfoResponseDto::of).collect(Collectors.toList());

    }
}