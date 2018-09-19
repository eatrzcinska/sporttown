package pl.sporttown.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private String name;
    @Column
    private String lastName;
    @Column
    private String password;
    @Column
    private String nick;

    @OneToMany (mappedBy = "user")
    private Set<Post> posts = new HashSet<>();
    @OneToMany (mappedBy = "comment_user")
    private Set<Comment> comments = new HashSet<>();



}
