package likelion.univ.adminUser.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UpdateUserRequestDto {

    private String name;
    private String major;
//    private String part;
    private Long ordinal;
    private String email;
//    private String role;


}