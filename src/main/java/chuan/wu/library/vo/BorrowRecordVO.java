/*
 * Copyright (c) Chuan.Wu $today.date
 */

package chuan.wu.library.vo;

import lombok.Data;

import java.util.List;

@Data
public class BorrowRecordVO {
    private Integer userId;

    List<BookVO> borrowedBooks;
}
