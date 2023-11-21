package likelion.univ.adminUser.usecase;

import likelion.univ.adminUser.dto.response.UserInfoResponseDto;
import likelion.univ.annotation.UseCase;
import likelion.univ.domain.user.entity.User;
import likelion.univ.domain.user.service.UserDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@UseCase
@RequiredArgsConstructor
public class FindAllUserUseCase {
    private final UserDomainService userDomainService;


    public List<UserInfoResponseDto> execute(int pageNum){
        Page<User> users = userDomainService.findAllUser(pageNum);
        return users.stream().map(UserInfoResponseDto::of).collect(Collectors.toList());

    }
}
