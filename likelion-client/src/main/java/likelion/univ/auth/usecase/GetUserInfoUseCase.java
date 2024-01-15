package likelion.univ.auth.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.auth.dto.response.AccountUserInfoDto;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import likelion.univ.domain.user.entity.User;
import likelion.univ.utils.AuthenticatedUserUtils;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GetUserInfoUseCase {

    private final AuthenticatedUserUtils authentiatedUserUtils;
    private final UserAdaptor userAdaptor;

    public AccountUserInfoDto execute(){
        Long currentUserId = authentiatedUserUtils.getCurrentUserId();
        User user = userAdaptor.findByIdWithUniversity(currentUserId);
        if(user.getUniversityInfo().getUniversity() == null)
            return AccountUserInfoDto.of(user, null);
        else return AccountUserInfoDto.of(user, user.getUniversityInfo().getUniversity().getName());
    }
}
