package likelion.univ.adminUser.usecase;

import likelion.univ.adminUser.dto.response.UserInfoResponseDto;
import likelion.univ.annotation.UseCase;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import likelion.univ.domain.user.entity.User;
import likelion.univ.utils.AuthentiatedUserUtils;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import java.util.stream.Collectors;
import org.springframework.data.domain.SliceImpl;

@UseCase
@RequiredArgsConstructor
public class FindAllByUnivAdminUseCase {

    private final UserAdaptor userAdaptor;
    private final AuthentiatedUserUtils authentiatedUserUtils;

    public Slice<UserInfoResponseDto> execute(Pageable pageable) {
        User user = authentiatedUserUtils.getCurrentUser();

        List<User> users = userAdaptor.findUsersByUniversityId(user.getUniversityInfo().getUniversity().getId(), pageable);
        List<UserInfoResponseDto> userInfoList = users.stream().map(UserInfoResponseDto::of).collect(Collectors.toList());

        boolean hasnext = !userInfoList.isEmpty() && userInfoList.size() > pageable.getPageSize();
        return new SliceImpl<>(userInfoList, pageable, hasnext);

    }
}