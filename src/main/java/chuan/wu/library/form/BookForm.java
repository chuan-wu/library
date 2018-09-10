/*
 * Copyright (c) Chuan.Wu $today.date
 */

package chuan.wu.library.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class BookForm {

    @NotEmpty
    private String title;

    @NotEmpty
    private String isbn;

    @NotEmpty
    private String author;
}
