package likelion.univ.domain.like.postlike.exception;

import static likelion.univ.constant.StaticValue.NOT_FOUND;

import likelion.univ.exception.base.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PostLikeErrorCode implements BaseErrorCode {
    LIKEPOST_NOT_FOUND(NOT_FOUND, "LIKEPOST_404", "LikePost가 존재하지 않습니다.");
    private final int httpStatus;
    private final String code;
    private final String message;
}
