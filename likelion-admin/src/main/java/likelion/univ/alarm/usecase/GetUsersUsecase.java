package likelion.univ.alarm.usecase;

import likelion.univ.alarm.dto.UserListDto;
import likelion.univ.annotation.UseCase;
import likelion.univ.domain.user.entity.User;
import likelion.univ.domain.user.repository.searchcondition.UserSearchCondition;
import likelion.univ.domain.user.service.UserDomainService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@UseCase
@RequiredArgsConstructor
public class GetUsersUsecase {

    private final UserDomainService userService;

    public UserListDto execute(UserSearchCondition condition) {
        List<UserListDto.UserDto> users = userService.findAll(condition).stream()
                .map(UserListDto.UserDto::new)
                .collect(Collectors.toList());

        return new UserListDto(users);
    }
}
