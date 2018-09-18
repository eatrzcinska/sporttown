package pl.sporttown.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String text;
    @Column
    private int votes=0;
    @Column(name = "DATA", nullable = false)
    private Date data;
}
