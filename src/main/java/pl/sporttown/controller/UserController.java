package pl.sporttown.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sporttown.controller.modelDTO.UserRegistrationDto;
import pl.sporttown.domain.model.User;
import pl.sporttown.service.UserService;
import javax.validation.Valid;

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
    @GetMapping("/profile/edit")
    public String editProfile(){
        return "profileEdit";
    }
    @GetMapping("/posty")
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
}
