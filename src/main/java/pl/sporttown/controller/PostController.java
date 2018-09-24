package pl.sporttown.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sporttown.controller.modelDTO.CommentDTO;
import pl.sporttown.controller.modelDTO.PostDTO;
import pl.sporttown.domain.model.Category;
import pl.sporttown.service.CommentService;
import pl.sporttown.service.PostService;
import pl.sporttown.service.UserService;

import java.security.Principal;
import java.util.List;


@Controller
public class PostController {

    private PostService postService;
    private UserService userService;
    private CommentService commentService;

    public PostController(PostService postService, UserService userService, CommentService commentService) {
        this.postService = postService;
        this.userService = userService;
        this.commentService = commentService;
    }

    @GetMapping("/")
    public String root(@ModelAttribute("postDTO") PostDTO postDTO, Model model) {
        model.addAttribute("postList", postService.findAll());
        model.addAttribute("categories",Category.values());
        return "index";
    }

    @PostMapping(path = "/post/add")
    public String addPost(@ModelAttribute("postDTO") PostDTO postDTO, Principal principal) {
        postService.addPost(postDTO, principal);
        return "redirect:/";
    }

    @GetMapping(path = "/list")
    public String postList(Model model) {
        model.addAttribute("postList", postService.findAll());
        return "postList";
    }

    @GetMapping(path = "/showpost/{postID}")
    public String showPost(@PathVariable("postID") long id, Model model){
        // User user = new User();
        CommentDTO commentDTO = new CommentDTO();
        PostDTO postDTObyId = postService.findPostById(id);

        List commentList = commentService.findCommentByPostId(id);
        //model.addAttribute(user);
        model.addAttribute("postDTOById", postDTObyId);
        model.addAttribute("commentDTO",commentDTO);
        model.addAttribute("commentList",commentList);

        return "onePostView";
    }
    @GetMapping("/showPostByCategory/{postCategory}")
    public String showPostsByCategory(@ModelAttribute("postDTO") PostDTO postDTO, Model model,
                                      @PathVariable("postCategory")String kategoria) {

        model.addAttribute("categories",Category.values());
        Category category = null;
        for (Category c : postDTO.getCategory().values()) {
            if (c.name().toUpperCase().equals(kategoria.toUpperCase())) {
                category = c;
                break;
            } else {
                category = null;
            }
        }

        List<PostDTO> postByCategories = postService.findCategory(category);
        model.addAttribute("postByCategories",postByCategories);
        return "postByCategory";
    }
}
