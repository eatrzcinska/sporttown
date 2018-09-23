package pl.sporttown.service;

import org.springframework.stereotype.Service;
import pl.sporttown.controller.modelDTO.PostDTO;
import pl.sporttown.controller.modelDTO.UserDTO;
import pl.sporttown.domain.model.Category;
import pl.sporttown.domain.model.Post;
import pl.sporttown.domain.model.User;
import pl.sporttown.domain.repoository.PostRepository;
import pl.sporttown.domain.repoository.UserRepository;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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

    public List<PostDTO> getAllPostByUser(String nick){
        List<Post> list = postRepository.findPostsByUserNick(nick);
        return list.stream()
                .map(post -> {
                    PostDTO postDTO = mappingService.mappingPostToPostDTO(post);
                    return postDTO;
                })
                .sorted(Comparator.comparing(PostDTO::getData).reversed())
                .collect(Collectors.toList());
    }

    public void delet(Long id){
        postRepository.deleteById(id);
    }

    public Post findPostEntity(Long id) {
        return postRepository.findPostById(id);
    }

    public PostDTO findPostById(Long id) {
       return mappingService.mappingPostToPostDTO(postRepository.findPostById(id));
    }
}


