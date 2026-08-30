package SpringBoot_Auth.demo.Controller;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import SpringBoot_Auth.demo.Entity.User;
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
                            RedirectAttributes redirectAttributes) {

            userService.registerNewUser(fullName, email, password);
            return "redirect:/login";
    }
}
