package pl.sporttown.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sporttown.controller.modelDTO.CommentDTO;
import pl.sporttown.controller.modelDTO.PostDTO;
import pl.sporttown.domain.model.Comment;
import pl.sporttown.domain.model.Post;
import pl.sporttown.domain.model.User;
import pl.sporttown.domain.repoository.CommentRepository;
import pl.sporttown.domain.repoository.PostRepository;
import pl.sporttown.domain.repoository.UserRepository;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CommentService {

    private CommentRepository commentRepository;
    private UserRepository userRepository;
    private MappingService mappingService;
    private PostService postService;
    private PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, UserRepository userRepository, MappingService mappingService, PostService postService, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.mappingService = mappingService;
        this.postService = postService;
        this.postRepository = postRepository;
    }

    public void addComment(CommentDTO commentDTO, Long id, Principal principal) {
        User user = userRepository.findByNick(principal.getName());
        Post post = postService.findPostEntity(id);
        commentDTO.setData(LocalDateTime.now());
        Comment comment = null;
        try {
            comment = mappingService.mapingCommentDTOToComment(commentDTO);
        } catch (IOException e) {
            e.printStackTrace();
        }
        comment.setPost(post);
        comment.setUser(user);
        commentRepository.save(comment);
    }

    public List<CommentDTO> findCommentByUserNick(String nick) {
        List<Comment> commentList = commentRepository.findCommentByUserNick(nick);
        return commentList.stream()
                .map(comment -> {
                    CommentDTO commentDTO = mappingService.mapingCommentToCommentDTO(comment);
                    return commentDTO;
                })
                .sorted(Comparator.comparing(CommentDTO::getData).reversed())
                .collect(Collectors.toList());
    }


    public List<CommentDTO> findCommentByPostId(Long id) {
        List<Comment> commentList = commentRepository.findCommentByPost_Id(id);
        return commentList.stream()
                .map(comment -> {
                    CommentDTO commentDTO = mappingService.mapingCommentToCommentDTO(comment);
                    return commentDTO;
                })
                .sorted(Comparator.comparing(CommentDTO::getData).reversed())
                .collect(Collectors.toList());
    }


}
