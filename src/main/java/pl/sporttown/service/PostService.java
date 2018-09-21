package pl.sporttown.service;

import org.springframework.stereotype.Service;
import pl.sporttown.controller.modelDTO.PostDTO;
import pl.sporttown.controller.modelDTO.UserDTO;
import pl.sporttown.domain.model.Post;
import pl.sporttown.domain.model.User;
import pl.sporttown.domain.repoository.PostRepository;
import pl.sporttown.domain.repoository.UserRepository;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class PostService {

    private PostRepository postRepository;
    private UserService userService;
    private MappingService mappingService;
    private UserRepository userRepository;

    public PostService(PostRepository postRepository, UserService userService, MappingService mappingService, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userService = userService;
        this.mappingService = mappingService;
        this.userRepository = userRepository;
    }

    public void addPost(PostDTO postDTO, Principal principal) {

        User user = userRepository.findByNick(principal.getName());
        postDTO.setData(LocalDateTime.now());

        Post post = null;
        try {
            post = mappingService.mappingPostDTOToPost(postDTO);
        } catch (IOException e) {
            e.printStackTrace();
        }
        post.setUser(user);
        postRepository.save(post);
    }

    public List<PostDTO> findAll() {
        List<Post> postList = postRepository.findAll();

        return postList.stream()
                .map(post -> {
                    PostDTO postDTO = mappingService.mappingPostToPostDTO(post);
                    return postDTO;
                })
                .sorted(Comparator.comparing(PostDTO::getData).reversed())
                .collect(Collectors.toList());
    }

    public PostDTO findPostById(Long id) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isPresent()) {
            Post nowy = post.get();
            return mappingService.mappingPostToPostDTO(nowy);
        } else {
            return null;
        }
    }
}


