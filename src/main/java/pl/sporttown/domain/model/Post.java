package pl.sporttown.domain.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private String text;
    @Column
    private int votes=0;
    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime data;

    @OneToMany (mappedBy = "comment_post")
    private Set<Comment> comments = new HashSet<>();

    @ManyToOne
    @JoinColumn (name="user_id")
    private User user;
}
