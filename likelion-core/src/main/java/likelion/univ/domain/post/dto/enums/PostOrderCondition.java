package likelion.univ.domain.post.dto.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PostOrderCondition {

    CREATED_DATE_ORDER("최신순"),
    LIKE_COUNT_ORDER("좋아요순"),
    COMMENT_COUNT_ORDER("댓글순");

    private final String title;
}
