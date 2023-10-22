package likelion.univ.domain.post.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MainCategory {
    HQ_BOARD("멋대 중앙"),
    FREE_BOARD("자유게시판"),
    OVERFLOW("멋사 오버플로우");

    private final String value;
}
