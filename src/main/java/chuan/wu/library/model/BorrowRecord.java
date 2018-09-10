/*
 * Copyright (c) Chuan.Wu $today.date
 */

package chuan.wu.library.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class BorrowRecord {

    @GeneratedValue
    @Id
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Book book;

    @ManyToOne(fetch= FetchType.LAZY)
    private User user;

    private Date date;
}
