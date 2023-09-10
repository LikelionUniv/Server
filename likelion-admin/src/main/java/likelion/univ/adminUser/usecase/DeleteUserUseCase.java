package likelion.univ.adminUser.usecase;

import likelion.univ.adminUser.dto.response.UserInfoResponseDto;
import likelion.univ.annotation.UseCase;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import likelion.univ.domain.user.entity.User;
import likelion.univ.domain.user.exception.ForbiddenAuthorization;
import likelion.univ.domain.user.service.UserDomainService;
import likelion.univ.utils.AuthentiatedUserUtils;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class DeleteUserUseCase {

    private final UserAdaptor userAdaptor;
    private final UserDomainService userDomainService;
    private final AuthentiatedUserUtils authentiatedUserUtils;

    public UserInfoResponseDto excute(Long userId, Long univId){
        User user=userAdaptor.findById(userId);
        User currentUser = authentiatedUserUtils.getCurrentUser();
        if (currentUser.getAuthInfo().getRole().equals("ADMIN")) {
            if (currentUser.getUniversityInfo().getUniversity().getId() == univId &&
              currentUser.getUniversityInfo().getUniversity().getId()==user.getUniversityInfo().getUniversity().getId()) {
                userDomainService.deleteUser(user);
            }else{
                throw new ForbiddenAuthorization();
            }
        }else if(currentUser.getAuthInfo().getRole().equals("SUPER_ADMIN")) {
            userDomainService.deleteUser(user);
        }else{
            throw new ForbiddenAuthorization();
        }
        return UserInfoResponseDto.of(user);
    }
}
