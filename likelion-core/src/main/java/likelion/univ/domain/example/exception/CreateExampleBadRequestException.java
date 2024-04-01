package likelion.univ.domain.example.exception;

import likelion.univ.exception.base.BaseException;

public class CreateExampleBadRequestException extends BaseException {

    public CreateExampleBadRequestException() {
        super(ExampleErrorCode.CREATE_EXAMPLE_BAD_REQUEST);
    }
}
