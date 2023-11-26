package likelion.univ.user.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.common.response.SliceResponse;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import likelion.univ.domain.user.entity.User;
import likelion.univ.user.dto.response.FollowUserInfoDto;
import likelion.univ.utils.AuthentiatedUserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class GetFollowInfoUseCase {
    private final AuthentiatedUserUtils authentiatedUserUtils;
    private final UserAdaptor userAdaptor;

    public SliceResponse<FollowUserInfoDto> executeForFollowing(Long userId, Pageable pageable){
        Slice<User> userSlice = userAdaptor.findFollowingUsersByFollowerID(userId, pageable);
        List<User> myFollowingUsers = getMyFollowingUsers(userSlice);

        return SliceResponse.of(userSlice.map(u ->
                FollowUserInfoDto.of(u, checkIFollowingTarget(u, myFollowingUsers))));
    }

    public SliceResponse<FollowUserInfoDto> executeForFollower(Long userId, Pageable pageable){
        Slice<User> userSlice = userAdaptor.findFollowerUsersByFollowingID(userId, pageable);
        List<User> myFollowingUsers = getMyFollowingUsers(userSlice);

        return SliceResponse.of(userSlice.map(u ->
                FollowUserInfoDto.of(u, checkIFollowingTarget(u, myFollowingUsers))));
    }
    private List<User> getMyFollowingUsers(Slice<User> userSlice){
        Long currentUserId = authentiatedUserUtils.getCurrentUserId();
        List<Long> followingUserIds = userSlice.getContent().stream().map(user -> user.getId()).toList();
        return userAdaptor.findMyFollowingUsersByFollowingIdIn(currentUserId, followingUserIds);
    }

    private Boolean checkIFollowingTarget(User target, List<User> myFollowingUsers){
        return myFollowingUsers.stream().anyMatch(u -> u.getId().equals(target.getId()));
    }
}
