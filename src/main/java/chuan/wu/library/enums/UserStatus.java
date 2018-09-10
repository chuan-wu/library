package chuan.wu.library.enums;

import lombok.Getter;

@Getter
public enum UserStatus {
    ACTIVE(0, "Active"),
    DISABLED(1, "Disabled");

    private int status;
    private String remark;

    UserStatus(Integer status, String remark) {
        this.status = status;
        this.remark = remark;
    }
}
