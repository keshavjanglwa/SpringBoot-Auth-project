package SpringBoot_Auth.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import SpringBoot_Auth.demo.Entity.Role;
import SpringBoot_Auth.demo.Entity.User;
import SpringBoot_Auth.demo.Repository.UserRepo;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserRepo userRepo ;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @GetMapping("/admin-home")
    public String adminHome(){
        return "adminpage";
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", userRepo.findAll());
        return "admin/users";
    }

    // Show "add user" form
    @GetMapping("/users/add")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());
        return "admin/add-user";
    }

    // Handle "add user" form submit
    @PostMapping("/users/add")
    public String addUser(@RequestParam String email,
                           @RequestParam String password,
                           Model model) {

        if (userRepo.existsByEmail(email)) {
            model.addAttribute("error", "Username already exists!");
            model.addAttribute("user", new User());
            return "admin/add-user";
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(Role.USER);
        user.setEnabled(true);
        userRepo.save(user);
        return "redirect:/admin/users";
    }

    // Activate / Deactivate a user
    @PostMapping("/users/{id}/toggle-status")
    public String toggleStatus(@PathVariable Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + id));
        user.setEnabled(!user.isEnabled());
        userRepo.save(user);
        return "redirect:/admin/users";
    }

    // // Lock / Unlock a user's account
    // @PostMapping("/users/{id}/toggle-lock")
    // public String toggleLock(@PathVariable Long id) {
    //     User user = userRepo.findById(id)
    //             .orElseThrow(() -> new IllegalArgumentException("User not found: " + id));
    //     user.setAccountNonLocked(!user.isAccountNonLocked());
    //     userRepository.save(user);
    //     return "redirect:/admin/users";
    // }

    @PostMapping("/users/{id}/delete")
    public String deleteUser(@PathVariable Long id) {
        userRepo.deleteById(id);
        return "redirect:/admin/users";
    }
}

 