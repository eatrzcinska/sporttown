package pl.sporttown.domain.repoository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sporttown.domain.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

}
