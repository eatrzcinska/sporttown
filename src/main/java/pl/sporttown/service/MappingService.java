package pl.sporttown.service;

import org.springframework.stereotype.Service;
import pl.sporttown.controller.modelDTO.PostDTO;
import pl.sporttown.controller.modelDTO.UserDTO;
import pl.sporttown.domain.model.Post;
import pl.sporttown.domain.model.User;

import java.util.stream.Collectors;

@Service
public class MappingService {

    public PostDTO mappingPostToPostDTO(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setUser(post.getUser());
        postDTO.setVotes(post.getVotes());
        postDTO.setData(post.getData());
        postDTO.setText(post.getText());
        postDTO.setImage(post.getImage());
        return postDTO;
    }

    public Post mappingPostDTOToPost(PostDTO postDTO) {
        Post post = new Post();
        post.setId(postDTO.getId());
        post.setUser(postDTO.getUser());
        post.setVotes(postDTO.getVotes());
        post.setData(postDTO.getData());
        post.setText(postDTO.getText());
        post.setImage(postDTO.getImage());
        return post;
    }

    public UserDTO mappingUserToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setLastName(user.getLastName());
        userDTO.setPassword(user.getPassword());
        userDTO.setNick(user.getNick());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }
    public User mappingUserDTOToUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setLastName(userDTO.getLastName());
        user.setPassword(userDTO.getPassword());
        user.setNick(userDTO.getNick());
        user.setEmail(userDTO.getEmail());
        return user;
    }


}