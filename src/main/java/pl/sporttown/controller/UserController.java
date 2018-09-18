package pl.sporttown.controller;

import org.springframework.stereotype.Service;
import pl.sporttown.service.UserService;

@Service
public class UserController {

    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }
}
