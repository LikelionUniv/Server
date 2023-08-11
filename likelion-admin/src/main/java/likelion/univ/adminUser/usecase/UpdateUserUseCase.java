package likelion.univ.adminUser.usecase;

import likelion.univ.adminUser.dto.request.UpdateUserRequestDto;
import likelion.univ.adminUser.dto.response.UserInfoResponseDto;
import likelion.univ.annotation.UseCase;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import likelion.univ.domain.user.entity.User;
import likelion.univ.domain.user.service.UserDomainService;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class UpdateUserUseCase {

    private final UserAdaptor userAdaptor;
    private final UserDomainService userDomainService;


    /*
    * 회원 정보 수정
    * GLOBAL_500
    * 서버 내부에서 알 수 없는 오류가 발생했습니다.
     */
    public UserInfoResponseDto updateUser(Long id, UpdateUserRequestDto updateUserRequestDto){
        User user = userAdaptor.findById(id);
        userDomainService.updateUser(user, updateUserRequestDto.getName(),
                updateUserRequestDto.getPart(),updateUserRequestDto.getMajor(),
                updateUserRequestDto.getEmail(), updateUserRequestDto.getOrdinal());
        return UserInfoResponseDto.of(user);
    }
}
