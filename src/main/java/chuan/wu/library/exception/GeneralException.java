package chuan.wu.library.exception;

import chuan.wu.library.utils.StatusCode;
import lombok.Data;

@Data
public class GeneralException extends RuntimeException {
    int code;

    public GeneralException(int code, String message) {
        super(message);
        this.code = code;
    }

    public GeneralException(StatusCode statusCode, String message) {
        super(message);
        this.code = statusCode.getCode();
    }
}
