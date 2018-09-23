package pl.sporttown.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sporttown.controller.modelDTO.UserDTO;
import pl.sporttown.controller.modelDTO.UserEditDTO;
import pl.sporttown.controller.modelDTO.UserRegistrationDto;
import pl.sporttown.domain.model.User;
import pl.sporttown.service.UserService;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto(){
        return new UserRegistrationDto();
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String showRegistrationForm() {
        return "registration";
    }

    @GetMapping("/profile")

    public String showProfie(){
        return "profile";
    }
    @ModelAttribute("userEdit")
    public UserDTO editUser(){
        return new UserDTO();
    }

    @GetMapping("/profile/edit")
    public String showEditProfil() {
        return "profileEdit.html";
    }

    @PostMapping(path = "/profile/edit")
    public String addPost(@ModelAttribute("userEdit") UserDTO userDTO, Principal principal) {
        userService.editProfile(userDTO,principal);
        return "redirect:/";
    }

    @GetMapping("/profile/posts")
    public String postsProfile(){
        return "profilePosts";
    }


    @PostMapping("/registration")
    public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userDto,
                                      BindingResult result)   {
        if (userService.findByEmail(userDto.getEmail()) != null || userService.findByNick(userDto.getNick()) != null) {
            return "redirect:/registration?failed";
        }
        if (result.hasErrors()) {
            return "registration";
        }
        userService.save(userDto);
        return "redirect:/registration?success";
    }

    @PostConstruct
    public void addUsers(){
        userService.addUsers();
    }
}
