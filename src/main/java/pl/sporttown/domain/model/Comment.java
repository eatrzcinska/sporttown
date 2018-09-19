package pl.sporttown.domain.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
public class Comment{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID;
    @Column
    private String text;
    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime data;

    @ManyToOne
    @JoinColumn (name="user_id")
    private Comment comment_user;

    @ManyToOne
    @JoinColumn (name="post_id")
    private Comment comment_post;


}
