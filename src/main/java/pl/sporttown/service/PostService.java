package pl.sporttown.service;
import org.springframework.stereotype.Service;
import pl.sporttown.controller.modelDTO.PostDTO;
import pl.sporttown.domain.model.Post;
import pl.sporttown.domain.repoository.PostRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private PostRepository postRepository;

    public PostService(PostRepository repository) {
        this.postRepository = repository;
    }

    public void addPost(PostDTO postDTO){
        Post post = new Post();
        post.setId(postDTO.getId());
        post.setText(postDTO.getText());
        post.setImage(postDTO.getImage());
        post.setData(postDTO.getData());
        post.setVotes(postDTO.getVotes());

        postRepository.save(post);
    }

    public List<Post> findAll(){
        return postRepository.findAll();
    }


}
