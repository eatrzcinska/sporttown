package pl.sporttown.domain.repoository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sporttown.domain.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

}
