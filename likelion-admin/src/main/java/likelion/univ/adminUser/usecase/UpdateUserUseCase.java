package likelion.univ.adminUser.usecase;

import likelion.univ.adminUser.dto.request.UpdateUserRequestDto;
import likelion.univ.adminUser.dto.response.UserInfoResponseDto;
import likelion.univ.annotation.UseCase;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import likelion.univ.domain.user.entity.Role;
import likelion.univ.domain.user.entity.User;
import likelion.univ.domain.user.exception.ForbiddenAuthorization;
import likelion.univ.domain.user.service.UserDomainService;
import likelion.univ.utils.AuthentiatedUserUtils;
import lombok.RequiredArgsConstructor;


@UseCase
@RequiredArgsConstructor
public class UpdateUserUseCase {

    private final UserAdaptor userAdaptor;
    private final UserDomainService userDomainService;
    private final AuthentiatedUserUtils authentiatedUserUtils;


    /*
    * 회원 정보 수정
    * GLOBAL_500
    * 서버 내부에서 알 수 없는 오류가 발생했습니다.
     */
    public UserInfoResponseDto excute(Long userId, Long univId,UpdateUserRequestDto updateUserRequestDto){
        User user;
        User currentUser = authentiatedUserUtils.getCurrentUser();
        if (currentUser.getAuthInfo().getRole().equals(Role.valueOf("ADMIN"))) {
            user = userAdaptor.findById(userId);
            if (currentUser.getUniversityInfo().getUniversity().getId() == univId &&
            currentUser.getUniversityInfo().getUniversity().getId()==user.getUniversityInfo().getUniversity().getId()) {
                userDomainService.updateUser(user, updateUserRequestDto.getName(),
                        updateUserRequestDto.getPart(), updateUserRequestDto.getMajor(),
                        updateUserRequestDto.getOrdinal());
            }else{
                throw new ForbiddenAuthorization();
            }
        }else if (currentUser.getAuthInfo().getRole().equals(Role.valueOf("SUPER_ADMIN"))) {
                    user = userAdaptor.findById(userId);
                        userDomainService.updateUser(user, updateUserRequestDto.getName(),
                                updateUserRequestDto.getPart(),updateUserRequestDto.getMajor(),
                                updateUserRequestDto.getOrdinal());
            } else{
                throw new ForbiddenAuthorization();
            }
        return UserInfoResponseDto.of(user);
    }
}
