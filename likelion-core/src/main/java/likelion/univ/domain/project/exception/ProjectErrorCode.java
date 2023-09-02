package likelion.univ.domain.project.exception;

import likelion.univ.exception.base.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static likelion.univ.constant.StaticValue.BAD_REQUEST;
import static likelion.univ.constant.StaticValue.NOT_FOUND;

@Getter
@AllArgsConstructor
public enum ProjectErrorCode implements BaseErrorCode {
    CREATE_PROJECT_BAD_REQUEST(BAD_REQUEST, "PROJECT_400", "프로젝트를 생성하는 데 잘못된 요청을 하였습니다."),
    PROJECT_NOT_FOUND(NOT_FOUND, "PROJECT_404", "프로젝트가 존재하지 않습니다.");

    private final int httpStatus;
    private final String code;
    private final String message;

}
