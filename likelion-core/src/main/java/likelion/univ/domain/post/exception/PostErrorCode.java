package likelion.univ.domain.post.exception;

import likelion.univ.exception.base.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static likelion.univ.constant.StaticValue.BAD_REQUEST;
import static likelion.univ.constant.StaticValue.NOT_FOUND;

@Getter
@AllArgsConstructor
public enum PostErrorCode implements BaseErrorCode {
    POST_NOT_FOUND(NOT_FOUND, "POST_404", "Post가 존재하지 않습니다."),
    NOT_AUTHORIZATION(BAD_REQUEST, "POST_400", "Post의 작성자가 아닙니다."),
    POST_SORT_TYPE_NOT_MATCHED(BAD_REQUEST, "POST_400_1", "해당 조건으로 게시글을 정렬할 수 없습니다.");

    private final int httpStatus;
    private final String code;
    private final String message;
}
