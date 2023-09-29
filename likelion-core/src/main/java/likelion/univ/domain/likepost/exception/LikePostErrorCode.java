package likelion.univ.domain.likepost.exception;

import likelion.univ.exception.base.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static likelion.univ.constant.StaticValue.NOT_FOUND;

@Getter
@AllArgsConstructor
public enum LikePostErrorCode implements BaseErrorCode {
    LIKEPOST_NOT_FOUND(NOT_FOUND, "LIKEPOST_404", "LikePost가 존재하지 않습니다.");

    private final int httpStatus;
    private final String code;
    private final String message;
}
