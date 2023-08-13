package likelion.univ.adminUser.usecase;

import likelion.univ.adminUser.dto.response.UserInfoResponseDto;
import likelion.univ.annotation.UseCase;
import likelion.univ.domain.university.adaptor.UniversityAdaptor;
import likelion.univ.domain.university.entity.University;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import likelion.univ.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@UseCase
@RequiredArgsConstructor
public class FindUserUseCase {
    private final UserAdaptor userAdaptor;

    public List<UserInfoResponseDto> findAllUser(int pageNum){
        Page<User> users = userAdaptor.findAllUser(pageNum);
        return users.stream().map(u -> new UserInfoResponseDto(u.getProfile().getName(),u.getId())).collect(Collectors.toList());
    }

    public User findUserById(Long id){
        User user = userAdaptor.findById(id);
        return user;
    }

    public UserInfoResponseDto findById(Long id){
        User user = userAdaptor.findById(id);
        return UserInfoResponseDto.of(user);
    }
    public List<UserInfoResponseDto> findUsersByUnivOfUser(Long userId){
        User user = findUserById(userId);
        List<User> users = userAdaptor.findUsersByUniversityId(user.getUniversityInfo().getUniversity().getId());
        return users.stream().map(u -> new UserInfoResponseDto(u.getProfile().getName(),u.getId())).collect(Collectors.toList());

    }
}
