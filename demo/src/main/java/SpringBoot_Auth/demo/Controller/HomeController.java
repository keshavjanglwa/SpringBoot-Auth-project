package SpringBoot_Auth.demo.Controller;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import SpringBoot_Auth.demo.Entity.User;
import SpringBoot_Auth.demo.Repository.UserRepo;
import SpringBoot_Auth.demo.Service.UserService;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    
    UserService userService;
    @Autowired
    UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;


    HomeController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
 
    @GetMapping("/home-page")
    public String HomePage(){
        return "home";
    } 
    @PostMapping("/register-admin")
    public String registerAdmin(@RequestBody User admin){
    userService.registerNewAdmin(admin.getFullname() , admin.getEmail() ,admin.getPassword());
        return "Admin is save"; 
    }
    
    @GetMapping("/forgot-password")
    public String forgetpassword() {
        return "forgot-password";
    }

    @PostMapping("/forgot-password")
    public String forgetpassword(@RequestParam String email, Model model) {
        User user = userRepo.findByEmail(email);
        if (user == null) {
            model.addAttribute("message", "User with this email does not exist");
            return "forgot-password";
        }
        String token = UUID.randomUUID().toString();

        user.setToken(token);
        user.setRestTokenExpiry(LocalDateTime.now().plusMinutes(10));
        userRepo.save(user);
        model.addAttribute("token", "YOUR TOKEN IS : " + token);

        return "forgot-password";
    }    
    @GetMapping("/reset-password")
    public String resertpassword() {
        return "reset-password"; 
    }

    @PostMapping("reset-password")
    public String resetPassword(@RequestParam String token, @RequestParam String password, Model model) {
        User user = userRepo.findByToken(token);
        if (user == null) {
            model.addAttribute("message", "Invalid Token");
            return "reset-password";
        }
        if (user.getRestTokenExpiry().isBefore(LocalDateTime.now())) {
            model.addAttribute("message", "Token is Expired");
            return "reset-password";
        }

        user.setToken(null);
        user.setRestTokenExpiry(null);
        user.setPassword(passwordEncoder.encode(password));
        userRepo.save(user);
        model.addAttribute("message", "Password is Changed Succesfully!");
        return "reset-password";
    }
     
}

