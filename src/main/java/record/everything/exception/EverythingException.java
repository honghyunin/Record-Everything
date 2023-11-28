package record.everything.exception;

import lombok.Getter;

@Getter
public class EverythingException extends RuntimeException {
    private final ErrorCode errorCode;

    public EverythingException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
