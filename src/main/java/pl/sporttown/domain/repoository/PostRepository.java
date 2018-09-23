package pl.sporttown.domain.repoository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sporttown.domain.model.Category;
import pl.sporttown.domain.model.Post;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

    public Post findPostById(Long id);
    List<Post> findAllByCategory(Category category);
}
