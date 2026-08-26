package SpringBoot_Auth.demo.Service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import SpringBoot_Auth.demo.Entity.Role;
import SpringBoot_Auth.demo.Entity.User;
import SpringBoot_Auth.demo.Repository.UserRepo;

@Service
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo userRepo;

    public User registerNewUser(String fullname , String email , String rawpassword){
        User user = new User();
        user.setFullname(fullname);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(rawpassword));
        user.setRole(Role.USER);
        return userRepo.save(user);
    }

    public User registerNewAdmin(String fullname , String email , String rawpassword){
        User admin = new User();
        admin.setFullname(fullname);
        admin.setEmail(email);
        admin.setPassword(passwordEncoder.encode(rawpassword));
        admin.setRole(Role.ADMIN);
        return userRepo.save(admin);
    }

    public Optional<User> findEmail(String email){
        return userRepo.findByEmail(email);
    } 
}
