package likelion.univ.adminUser.usecase;

import likelion.univ.adminUser.dto.response.UserInfoResponseDto;
import likelion.univ.annotation.UseCase;
import likelion.univ.common.response.SliceResponse;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import likelion.univ.domain.user.entity.User;
import likelion.univ.utils.AuthenticatedUserUtils;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

@UseCase
@RequiredArgsConstructor
public class FindAllByUnivAdminUseCase {

    private final UserAdaptor userAdaptor;
    private final AuthenticatedUserUtils authentiatedUserUtils;

    public SliceResponse<UserInfoResponseDto> execute(Pageable pageable) {
        User user = authentiatedUserUtils.getCurrentUser();

        Slice<User> users = userAdaptor.findUsersByUniversityId(user.getUniversityInfo().getUniversity().getId(), pageable);

        return SliceResponse.of(users.map(u->UserInfoResponseDto.of(u)));
    }
}