package likelion.univ.domain.project.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Output {
    WEB("OUTPUT_WEB", "웹"),
    ANDROID("OUTPUT_AND", "안드로이드"),
    IOS("OUTPUT_IOS", "아이폰");

    private final String key;
    private final String title;

}
