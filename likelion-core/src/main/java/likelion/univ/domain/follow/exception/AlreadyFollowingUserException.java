package likelion.univ.domain.follow.exception;

import likelion.univ.exception.base.BaseException;

public class AlreadyFollowingUserException extends BaseException {
    public AlreadyFollowingUserException() {
        super(FollowErrorCode.ALREADY_FOLLOWING_USER);
    }
}
