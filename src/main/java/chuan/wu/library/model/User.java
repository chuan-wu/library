package chuan.wu.library.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class User {

    @GeneratedValue
    @Id
    private Integer id;
    private String username;
    private String password;
    private int status;

    @OneToMany(fetch= FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy="user")
    public List<BorrowRecord> borrowRecordList;
}
