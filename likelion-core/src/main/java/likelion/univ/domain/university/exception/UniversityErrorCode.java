package likelion.univ.domain.university.exception;

import static likelion.univ.constant.StaticValue.NOT_FOUND;

import likelion.univ.exception.base.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UniversityErrorCode implements BaseErrorCode {
    UNIVERSITY_NOT_FOUND(NOT_FOUND, "UNIVERSITY_404", "해당 대학을 찾을 수 없습니다.");
    private final int httpStatus;
    private final String code;
    private final String message;

}
