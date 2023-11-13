package likelion.univ.domain.follow.exception;

import likelion.univ.exception.base.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static likelion.univ.constant.StaticValue.*;

@Getter
@AllArgsConstructor
public enum FollowErrorCode implements BaseErrorCode {
    ALREADY_FOLLOWING_USER(BAD_REQUEST, "FOLLOW_400_1", "이미 팔로우하고 있습니다.");


    private final int httpStatus;
    private final String code;
    private final String message;
}
