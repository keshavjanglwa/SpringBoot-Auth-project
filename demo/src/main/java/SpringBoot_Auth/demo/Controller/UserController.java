package SpringBoot_Auth.demo.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @GetMapping("/user-home")
    public String UserHome(){
        return "YOU ARE IN THE USER PAGE";
    }
}


