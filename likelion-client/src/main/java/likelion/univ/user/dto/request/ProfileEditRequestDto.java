package likelion.univ.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import likelion.univ.domain.user.entity.Part;
import likelion.univ.domain.user.entity.Profile;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;


@Getter
@NoArgsConstructor
public class ProfileEditRequestDto {
    @NotNull
    @Schema(description = "이름", example = "김슬기", required = true)
    private String name;

    @Schema(description = "한줄 소개", example = "안녕하세요. 김슬기입니다.", required = false)
    private String introduction;

    @Schema(description = "프로필 이미지", example = "", required = false)
    private String profileImage;

    @NotNull
    @Schema(description = "트랙(파트)", example = "BACKEND", required = true, enumAsRef = true)
    private Part part;

    public Profile toProfile(){
        return Profile.builder()
                .name(name)
                .profileImage(profileImage)
                .introduction(introduction)
                .part(part)
                .build();
    }
}