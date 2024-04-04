package likelion.univ.exception;

import likelion.univ.exception.base.BaseException;

public class PublicKeyGenerationException extends BaseException {

    public PublicKeyGenerationException() {
        super(GlobalErrorCode.PUBKEY_GENERATION_FAILED);
    }
}
