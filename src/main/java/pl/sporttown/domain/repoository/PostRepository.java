package pl.sporttown.domain.repoository;

import javafx.geometry.Pos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sporttown.domain.model.Post;


import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

    Post findPostById(Long id);
    List<Post> findPostsByUserNick(String nick);


}