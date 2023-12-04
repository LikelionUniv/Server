package likelion.univ.domain.donation_history.exception;

import likelion.univ.exception.base.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static likelion.univ.constant.StaticValue.*;

@Getter
@AllArgsConstructor
public enum DonationHistoryErrorCode implements BaseErrorCode {
    DONATION_HISTORY_NOT_FOUND(NOT_FOUND, "DONATION_HISTORY_404", "기부금 내역 게시글을 찾을 수 없습니다.");


    private final int httpStatus;
    private final String code;
    private final String message;
}
