package likelion.univ.domain.like.commentlike.exception;

import likelion.univ.exception.base.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static likelion.univ.constant.StaticValue.BAD_REQUEST;

@Getter
@RequiredArgsConstructor
public enum CommentLikeErrorCode implements BaseErrorCode {
    CREATE_LIKECOMMENT_BAD_REQUEST(BAD_REQUEST, "COMMENT_400", "댓글 좋아요를 생성하는 데 잘못된 요청을 하였습니다.");

    private final int httpStatus;
    private final String code;
    private final String message;
}
