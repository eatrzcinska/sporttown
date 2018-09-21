package pl.sporttown.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sporttown.controller.modelDTO.PostDTO;
import pl.sporttown.controller.modelDTO.UserDTO;
import pl.sporttown.domain.model.Category;
import pl.sporttown.domain.model.Post;
import pl.sporttown.domain.model.User;
import pl.sporttown.service.PostService;
import pl.sporttown.service.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Controller
public class PostController {

    private PostService postService;
    private UserService userService;

    public PostController(PostService postService,UserService userService) {
        this.postService = postService;
        this.userService = userService;
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

    @GetMapping(path = "/post/show/{postID}")
    public String showPost(@PathVariable("postID") long id, Model model){
        // User user = new User();
        PostDTO postDTObyId = postService.findPostById(id);
        //model.addAttribute(user);
        model.addAttribute("postDTOById", postDTObyId);
        return "onePostView";
    }


}

