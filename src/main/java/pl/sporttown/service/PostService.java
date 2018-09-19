package pl.sporttown.service;
import org.springframework.stereotype.Service;
import pl.sporttown.domain.model.Post;
import pl.sporttown.domain.repoository.PostRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public void create(Post post){
        repository.save(post);
    }

    public List<Post> findAll(){
        return repository.findAll();
    }

    public Optional<Post> findByDate(Date data) {
        return repository.findAllByData(data);
    }

    public Optional<Post> findById(Long id) {
        return repository.findById(id);
    }

    public void removePost(Long id){
        repository.deleteById(id);
    }


}
