package likelion.univ.exception.base;

public interface BaseErrorCode {

    String getCode();

    String getMessage();

    int getHttpStatus();
}
