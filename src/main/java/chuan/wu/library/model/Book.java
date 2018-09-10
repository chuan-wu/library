package chuan.wu.library.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Book {
    @GeneratedValue
    @Id
    private Integer id;
    private String isbn;
    private String title;
    private String author;
    private int status;

    @OneToMany(fetch= FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy="book")
    public List<BorrowRecord> borrowRecordList;
}
