package pl.sporttown.domain.repoository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.sporttown.domain.model.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

    public List<Comment> findCommentByPost_Id(Long id);
    List<Comment> findCommentByUserNick(String nick);

//
//    @Query("SELECT u FROM COMMENT u WHERE post_id = :id")
//    List <Comment> szukaj (@Param("id") Long id);

}
