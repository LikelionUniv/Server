package likelion.univ.user.service;

import likelion.univ.domain.user.entity.Profile;
import likelion.univ.domain.user.service.UserService;
import likelion.univ.user.dto.request.ProfileEditRequestDto;
import likelion.univ.utils.AuthenticatedUserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ClientUserService {

    private final AuthenticatedUserUtils authenticatedUserUtils;
    private final UserService userService;

    public void editProfile(Long userId, ProfileEditRequestDto profileEditRequestDto) {
        authenticatedUserUtils.checkIdentification(userId);
        Profile profile = profileEditRequestDto.toProfile();
        userService.editProfile(userId, profile);
    }
}
