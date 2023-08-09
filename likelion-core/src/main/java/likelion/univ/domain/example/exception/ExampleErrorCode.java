package likelion.univ.domain.example.exception;

import likelion.univ.exception.base.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static likelion.univ.constant.StaticValue.BAD_REQUEST;
import static likelion.univ.constant.StaticValue.NOT_FOUND;

@Getter
@AllArgsConstructor
public enum ExampleErrorCode implements BaseErrorCode {
    CREATE_EXAMPLE_BAD_REQUEST(BAD_REQUEST, "EXAMPLE_400", "example을 생성하는 데 잘못된 요청을 하였습니다."),
    EXAMPLE_NOT_FOUND(NOT_FOUND, "EXAMPLE_404", "예시가 존재하지 않습니다.");

    private final int httpStatus;
    private final String code;
    private final String message;
}

