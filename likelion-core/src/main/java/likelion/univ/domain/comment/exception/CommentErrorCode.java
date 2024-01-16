package likelion.univ.domain.comment.exception;

import likelion.univ.exception.base.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static likelion.univ.constant.StaticValue.BAD_REQUEST;
import static likelion.univ.constant.StaticValue.NOT_FOUND;
@Getter
@RequiredArgsConstructor
public enum CommentErrorCode implements BaseErrorCode {
    CREATE_COMMENT_BAD_REQUEST(BAD_REQUEST, "COMMENT_400", "댓글을 생성하는 데 잘못된 요청을 하였습니다."),
    AUTHOR_NOT_DETECTED(BAD_REQUEST, "COMMENT_400", "작성자가 아닙니다."),
    COMMENT_NOT_FOUND(NOT_FOUND, "COMMENT_404", "댓글이 존재하지 않습니다."),
    COMMENT_ALREADY_DELETED(BAD_REQUEST, "COMMENT_400", "이미 삭제된 댓글입니다.");

    private final int httpStatus;
    private final String code;
    private final String message;
}
