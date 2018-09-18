package pl.sporttown.repoository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.sporttown.model.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
}
