package pl.sporttown.repoository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.sporttown.model.User;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {

}
