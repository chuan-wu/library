package chuan.wu.library.enums;

import lombok.Getter;

@Getter
public enum BookStatus {
    AVAILABLE(0, "available"),
    BORROWED(1, "borrowed");

    private int status;
    private String remark;

    BookStatus(int status, String remark) {
        this.status = status;
        this.remark = remark;
    }
}
