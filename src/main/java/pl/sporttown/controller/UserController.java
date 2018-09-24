package pl.sporttown.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sporttown.controller.modelDTO.UserDTO;
import pl.sporttown.controller.modelDTO.UserRegistrationDto;
import pl.sporttown.domain.model.Category;
import pl.sporttown.domain.model.User;
import pl.sporttown.service.CommentService;
import pl.sporttown.service.PostService;
import pl.sporttown.service.UserService;
import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

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
    public String showProfie(Model model, Principal principal){

        model.addAttribute("userinfo",userService.findByNick(principal.getName()));
        model.addAttribute("categories",Category.values());

        return "profile";
    }

    @GetMapping("/profile/comments")
    public String showComments(Model model, Principal principal){
        model.addAttribute("post",postService.getAllPostByUser(principal.getName()));
        model.addAttribute("userinfo",userService.findByNick(principal.getName()));
        model.addAttribute("comments", commentService.findCommentByUserNick(principal.getName()));
        return "profileComments";
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
        return "redirect:/profile";
    }

    @GetMapping("/profile/posts")
    public String postsProfile( Model model, Principal principal){
        model.addAttribute("userinfo", userService.findByNick(principal.getName()));
        model.addAttribute("posts",postService.getAllPostByUser(principal.getName()));
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

//    @PostConstruct
//    public void addUsers(){
//        userService.addUsers();
//    }
}
