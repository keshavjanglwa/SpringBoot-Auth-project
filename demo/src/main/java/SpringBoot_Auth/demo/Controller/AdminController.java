package SpringBoot_Auth.demo.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    
    @GetMapping("/admin-home")
    public String adminHome(){
        return "YOU ARE IN THE ADMIN PAGE ";
    }
}

 