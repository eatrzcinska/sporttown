package pl.sporttown.controller;

import org.springframework.stereotype.Controller;
import pl.sporttown.service.PostService;


@Controller
public class PostController {

    private PostService service;

    public PostController(PostService service) {
        this.service = service;
    }
}
