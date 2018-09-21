package pl.sporttown.service;
import org.springframework.stereotype.Service;
import pl.sporttown.domain.repoository.CommentRepository;

@Service
public class CommentService {

    private CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }


}
