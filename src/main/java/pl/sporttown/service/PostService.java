package pl.sporttown.service;

import org.springframework.stereotype.Service;
import pl.sporttown.domain.repoository.PostRepository;

@Service
public class PostService {

    private PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }
}
