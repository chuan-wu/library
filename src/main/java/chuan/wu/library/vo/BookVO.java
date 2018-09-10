/*
 * Copyright (c) Chuan.Wu $today.date
 */

package chuan.wu.library.vo;

import lombok.Data;

@Data
public class BookVO {
    private Integer id;
    private String isbn;
    private String title;
    private String author;
    private int status;
}
