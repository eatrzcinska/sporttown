package pl.sporttown.model;

import lombok.Data;
import org.springframework.data.repository.CrudRepository;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Comment{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;
    @Column
    private String text;
    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date data;

    @ManyToOne
    @JoinColumn (name="user_id")
    private Comment comment_user;

    @ManyToOne
    @JoinColumn (name="post_id")
    private Comment comment_post;


}
