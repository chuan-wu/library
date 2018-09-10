/*
 * Copyright (c) Chuan.Wu $today.date
 */

package chuan.wu.library.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserForm {
    @NotEmpty
    private String username;

    @NotEmpty
    private String password;
}
