package likelion.univ.domain.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LoginType {
    KAKAO("kakao"),
    GOOGLE("google");

    private String value;
}
