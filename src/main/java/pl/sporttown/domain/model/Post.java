package pl.sporttown.domain.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"comments", "user"})
@ToString(exclude = {"comments", "user"})
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

    @OneToMany (mappedBy = "post", cascade = CascadeType.REMOVE)
    private Set<Comment> comments = new HashSet<>();

    @ManyToOne
    @JoinColumn (name="user_id")
    private User user;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] image;
    @Column
    private Category category;
}
