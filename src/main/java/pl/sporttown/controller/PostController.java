package pl.sporttown.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sporttown.domain.model.Post;
import pl.sporttown.service.PostService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequestMapping(path = "/post/")
@Controller
public class PostController {

    private PostService service;

    public PostController(PostService service) {
        this.service = service;
    }


    @GetMapping(path = "/add")
    public String add(@ModelAttribute ("post") Post post) {
        return "postAdd";
    }

    @PostMapping(path = "/add")
    public String addPost(Post post) {
        service.addPost(post);
        return "redirect:/post/list";
    }

    @GetMapping(path = "/list")
    public String postList(Model model) {
        List<Post> postList = service.findAll();
        model.addAttribute("postList", postList);
        return "postList";
    }


}
