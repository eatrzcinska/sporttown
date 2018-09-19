package pl.sporttown.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sporttown.model.Post;
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
    public String add(Model model) {
        Post post = new Post();
        model.addAttribute("post", post);
        return "postAdd";
    }

    @PostMapping(path = "/add")
    public String createPost(Post post){
        service.create(post);
        return "redirect:/post/list";
    }

    @GetMapping(path = "/list")
    public String postList(Model model){
        List <Post> postList = service.findAll();
        model.addAttribute("postList", postList);
        return "postList";
    }

    //Do zmiany
    @GetMapping(path = "/find/{data}")
    public String findPostByDate(@PathVariable(name = "data")Date data, Model model){
        Optional<Post> postsByData = service.findByDate(data);

        if (postsByData.isPresent()) {
            model.addAttribute("post", postsByData.get());
            return "redirect:/post/list";
        }
        return "redirect:/error";
    }

    //Do zmiany
    @GetMapping(path = "/find/{id}")
    public String findPostById(@PathVariable(name = "id") Long id, Model model){
            Optional<Post> postsById = service.findById(id);

            if (postsById.isPresent()) {
                model.addAttribute("post", postsById.get());
                return "postDetails";
            }
            return "redirect:/error";
        }

    @DeleteMapping(path = "/remove/{id}")
    public String removePost(@PathVariable(name = "id")Long id){
        service.removePost(id);
        return "redirect:/post/list";
    }

}
