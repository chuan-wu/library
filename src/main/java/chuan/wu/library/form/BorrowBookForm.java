/*
 * Copyright (c) Chuan.Wu $today.date
 */

package chuan.wu.library.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class BorrowBookForm {

    @NotEmpty
    private List<Integer> bookIds;

    @NotNull
    private Integer userId;
}
