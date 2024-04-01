package likelion.univ.domain.example.exception;

import likelion.univ.exception.base.BaseException;

public class ExampleNotFoundException extends BaseException {
    public ExampleNotFoundException() {
        super(ExampleErrorCode.EXAMPLE_NOT_FOUND);
    }
}
