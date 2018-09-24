package pl.sporttown.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sporttown.controller.modelDTO.CommentDTO;
import pl.sporttown.service.CommentService;

import java.security.Principal;

@Controller
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping(path = "showpost/addcomment/{postID}")
    public String addPost(@PathVariable("postID") Long id, CommentDTO commentDTO, Principal principal, Model model) {
        commentService.addComment(commentDTO,id, principal);
        model.addAttribute("commentDTO",commentDTO);
        return "redirect:/showpost/{postID}";
    }
}



//    @GetMapping("/")
//    public String root(@ModelAttribute("postDTO") PostDTO postDTO, Model model) {
//        model.addAttribute("postList", postService.findAll());
//        model.addAttribute("categories",Category.values());
//        return "index";
//    }