package SpringBoot_Auth.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import SpringBoot_Auth.demo.Entity.User;
import SpringBoot_Auth.demo.Service.UserService;

@RestController
@RequestMapping("/home")
public class HomeController {
    @Autowired
    UserService userService;
    
    private final PasswordEncoder passwordEncoder;


    HomeController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
 
    @GetMapping("/home-page")
    public String HomePage(){
        return "YOU ARE IN THE HOME PAGE";
    }
    
    @PostMapping("/register-user")
    public String registeruser(@RequestBody User user){
    userService.registerNewUser(user.getFullname() , user.getEmail() ,user.getPassword());
        return "User is save";
    }
    @PostMapping("/register-admin")
    public String registerAdmin(@RequestBody User admin){
    userService.registerNewAdmin(admin.getFullname() , admin.getEmail() ,admin.getPassword());
        return "Admin is save"; 
    }
     
}

