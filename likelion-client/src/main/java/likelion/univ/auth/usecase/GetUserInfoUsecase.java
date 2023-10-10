package likelion.univ.auth.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.auth.dto.response.AccountTokenDto;
import likelion.univ.auth.dto.response.AccountUserInfoDto;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import likelion.univ.domain.user.entity.LoginType;
import likelion.univ.domain.user.entity.User;
import likelion.univ.jwt.dto.UserInfoFromIdToken;
import likelion.univ.utils.AuthentiatedUserUtils;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GetUserInfoUsecase {

    private final AuthentiatedUserUtils authentiatedUserUtils;

    public AccountUserInfoDto execute(){
        User user = authentiatedUserUtils.getCurrentUser();
        return AccountUserInfoDto.of(user);
    }
}
