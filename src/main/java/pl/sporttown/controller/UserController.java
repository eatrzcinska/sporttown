package pl.sporttown.controller;

import org.springframework.stereotype.Controller;
import pl.sporttown.service.UserService;

@Controller
public class UserController {

    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }
}
