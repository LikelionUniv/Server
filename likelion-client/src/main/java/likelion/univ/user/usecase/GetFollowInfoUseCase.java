package likelion.univ.user.usecase;

import java.util.List;
import likelion.univ.annotation.UseCase;
import likelion.univ.common.response.SliceResponse;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import likelion.univ.domain.user.entity.User;
import likelion.univ.user.dto.response.FollowUserInfoDto;
import likelion.univ.utils.AuthenticatedUserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

@UseCase
@RequiredArgsConstructor
public class GetFollowInfoUseCase {
    private final AuthenticatedUserUtils authenticatedUserUtils;
    private final UserAdaptor userAdaptor;

    public SliceResponse<FollowUserInfoDto> executeForFollowing(Long userId, Pageable pageable) {
        Slice<User> userSlice = userAdaptor.findFollowingUsersByFollowerID(userId, pageable);
        List<User> myFollowingUsers = getMyFollowingUsers(userSlice);

        return SliceResponse.of(userSlice.map(u ->
                FollowUserInfoDto.of(u, checkIFollowingTarget(u, myFollowingUsers))));
    }

    public SliceResponse<FollowUserInfoDto> executeForFollower(Long userId, Pageable pageable) {
        Slice<User> userSlice = userAdaptor.findFollowerUsersByFollowingID(userId, pageable);
        List<User> myFollowingUsers = getMyFollowingUsers(userSlice);

        return SliceResponse.of(userSlice.map(u ->
                FollowUserInfoDto.of(u, checkIFollowingTarget(u, myFollowingUsers))));
    }

    private List<User> getMyFollowingUsers(Slice<User> userSlice) {
        Long currentUserId = authenticatedUserUtils.getCurrentUserId();
        List<Long> followingUserIds = userSlice.getContent().stream().map(user -> user.getId()).toList();
        return userAdaptor.findMyFollowingUsersByFollowingIdIn(currentUserId, followingUserIds);
    }

    private Boolean checkIFollowingTarget(User target, List<User> myFollowingUsers) {
        return myFollowingUsers.stream().anyMatch(u -> u.getId().equals(target.getId()));
    }
}
