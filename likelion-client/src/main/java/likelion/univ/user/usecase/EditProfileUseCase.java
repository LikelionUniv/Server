package likelion.univ.user.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.user.entity.Profile;
import likelion.univ.domain.user.entity.User;
import likelion.univ.domain.user.service.UserDomainService;
import likelion.univ.user.dto.request.ProfileEditRequestDto;
import likelion.univ.user.dto.response.ProfileDetailsDto;
import likelion.univ.utils.AuthenticatedUserUtils;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class EditProfileUseCase {
    private final AuthenticatedUserUtils authentiatedUserUtils;
    private final UserDomainService userDomainService;

    public void execute(Long userId, ProfileEditRequestDto profileEditRequestDto){
        authentiatedUserUtils.checkidentification(userId);

        Profile profile = profileEditRequestDto.toProfile();
        User user = userDomainService.editProfile(userId, profile);
    }
}
