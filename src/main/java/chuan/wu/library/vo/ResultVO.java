/*
 * Copyright (c) Chuan.Wu $today.date
 */

package chuan.wu.library.vo;

import lombok.Data;

@Data
public class ResultVO<T> {

    public static ResultVO success() {
        return new ResultVO<>(200, "success");
    }

    public static ResultVO success(Object data) {
        return new ResultVO(200, "success", data);
    }

    private int code;
    private String message;

    public ResultVO(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultVO(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private T data;
}
