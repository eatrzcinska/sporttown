package pl.sporttown.model;

import javafx.geometry.Pos;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany (mappedBy = "comment_post")
    private Set<Comment> comments = new HashSet<>();

    @ManyToOne
    @JoinColumn (name="user_id")
    private User user;
}
