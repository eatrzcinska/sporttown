package pl.sporttown.repoository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.sporttown.model.Post;

@Repository
public interface PostRepository extends CrudRepository<Post,Long> {

}
