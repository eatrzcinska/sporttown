package pl.sporttown.service;

import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import pl.sporttown.controller.modelDTO.PostDTO;
import pl.sporttown.controller.modelDTO.UserDTO;
import pl.sporttown.controller.modelDTO.UserRegistrationDto;
import pl.sporttown.domain.model.Post;
import pl.sporttown.domain.model.User;

import java.io.IOException;
import java.util.Base64;
import java.util.stream.Collectors;

@Service
public class MappingService {

    public PostDTO mappingPostToPostDTO(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setUserDTO(mappingUserToUserDTO(post.getUser()));
        postDTO.setVotes(post.getVotes());
        postDTO.setData(post.getData());
        postDTO.setText(post.getText());
        postDTO.setImageBase64Encoded(Base64.getEncoder().encodeToString(post.getImage()));
        postDTO.setCategory(post.getCategory());
        return postDTO;
    }

    public Post mappingPostDTOToPost(PostDTO postDTO) throws IOException {
        Post post = new Post();
        post.setId(postDTO.getId());
        post.setVotes(postDTO.getVotes());
        post.setData(postDTO.getData());
        post.setText(postDTO.getText());
        post.setImage(postDTO.getImage().getBytes());
        post.setCategory(postDTO.getCategory());
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

    public User mappingUserDTOToUser(UserRegistrationDto userRegistrationDto) {
        User user = new User();
        user.setName("");
        user.setLastName("");
        user.setPassword(userRegistrationDto.getPassword());
        user.setNick(userRegistrationDto.getNick());
        user.setEmail(userRegistrationDto.getEmail());
        return user;
    }
}