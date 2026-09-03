package SpringBoot_Auth.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import SpringBoot_Auth.demo.Repository.UserRepo;
import SpringBoot_Auth.demo.Service.UserService;

@Controller
public class AuthController {
    @Autowired
    UserService userService;
    @Autowired
    UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }
    @GetMapping("/register-user")
    public String registerPage() {
        return "register";
    }
    @PostMapping("/register-user")
    public String register(@RequestParam String fullName,
                            @RequestParam String email,
                            @RequestParam String password,
                            @RequestParam String confirmPassword,
                            Model model) {
                if (!password.equals(confirmPassword)) {
                    model.addAttribute("error", "Passwords do not match!");
                    return "/register";
                }
                userService.registerNewUser(fullName, email, password);
                return "redirect:/login";
    }
}
