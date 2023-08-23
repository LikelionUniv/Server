package likelion.univ.domain.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LoginType {
    KAKAO("KAKAO"),
    GOOGLE("GOOGLE");

    private String value;
}
