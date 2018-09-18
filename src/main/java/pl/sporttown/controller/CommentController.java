package pl.sporttown.controller;

import org.springframework.stereotype.Controller;
import pl.sporttown.service.CommentService;

@Controller
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
}
