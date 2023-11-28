package record.everything.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // *** SERVER ERROR *** //
    UNKNOWN_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "internal server error"),

    // *** GLOBAL *** //
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "bad request");

    private final HttpStatus httpStatus;
    private final String message;
}
