package likelion.univ.auth.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.auth.dto.response.AccountUserInfoDto;
import likelion.univ.domain.user.entity.User;
import likelion.univ.utils.AuthentiatedUserUtils;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GetUserInfoUseCase {

    private final AuthentiatedUserUtils authentiatedUserUtils;

    public AccountUserInfoDto execute(){
        User user = authentiatedUserUtils.getCurrentUser();
        return AccountUserInfoDto.of(user);
    }
}
