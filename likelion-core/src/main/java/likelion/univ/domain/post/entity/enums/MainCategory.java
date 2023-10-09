package likelion.univ.domain.post.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MainCategory {
    HQ("HQ"),
    BOARD("BOARD"),
    OVERFLOW("OVERFLOW");


    private final String value;
}
