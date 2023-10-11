package likelion.univ.mypage.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.follow.adaptor.FolllowAdaptor;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import likelion.univ.domain.user.entity.User;
import likelion.univ.mypage.dto.response.ProfileDetailsDto;
import likelion.univ.utils.AuthentiatedUserUtils;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GetProfileUseCase {
    private final AuthentiatedUserUtils authentiatedUserUtils;
    private final UserAdaptor userAdaptor;
    private final FolllowAdaptor folllowAdaptor;

    public ProfileDetailsDto execute(){
        Long userId = authentiatedUserUtils.getCurrentUserId();
        User user = userAdaptor.findByIdWithUniversity(userId);
        /*임시로 카운트쿼리로.. */
        Long followerNum = folllowAdaptor.countByFolloweeId(userId);
        Long followingNum = folllowAdaptor.countByFollowerId(userId);

        return ProfileDetailsDto.of(user, followerNum, followingNum);
    }
}
