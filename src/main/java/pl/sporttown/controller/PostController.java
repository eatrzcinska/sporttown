package pl.sporttown.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sporttown.controller.modelDTO.PostDTO;
import pl.sporttown.domain.model.Post;
import pl.sporttown.service.PostService;

import java.util.List;


@Controller
public class PostController {

    private PostService postService;

    public PostController(PostService service) {
        this.postService = service;
    }

    @GetMapping("/")
    public String root(@ModelAttribute("postDTO") PostDTO postDTO) {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }



    @PostMapping(path = "/post/add")
    public String addPost(@ModelAttribute("postDTO") PostDTO postDTO) {
        postService.addPost(postDTO);
        return "redirect:/";

    }


    @GetMapping(path = "/list")
    public String postList(Model model) {
        List<Post> postList = postService.findAll();
        model.addAttribute("postList", postList);
        return "postList";
    }

}

