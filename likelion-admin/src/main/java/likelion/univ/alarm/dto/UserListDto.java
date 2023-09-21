package likelion.univ.alarm.dto;

import likelion.univ.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class UserListDto {

    List<UserDto> users;

    public UserListDto(List<UserDto> users) {
        this.users = users;
    }

    @Getter
    @NoArgsConstructor
    public static class UserDto {
        private String email;
        private String name;
        private String university;
        private String part;

        public UserDto(User user) {
            this.email = user.getAuthInfo().getEmail();
            this.name = user.getProfile().getName();
            this.university = user.getUniversityInfo().getUniversity().getName();
            this.part = user.getProfile().getPart().getValue();
        }
    }

}
