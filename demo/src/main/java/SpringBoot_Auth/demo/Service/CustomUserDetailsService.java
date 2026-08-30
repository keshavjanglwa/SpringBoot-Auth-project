package SpringBoot_Auth.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import SpringBoot_Auth.demo.Entity.User;
import SpringBoot_Auth.demo.Repository.UserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {
   
    @Autowired 
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return org.springframework.security.core.userdetails.User
        .withUsername(user.getEmail())
        .password(user.getPassword())
        .authorities(user.getRole().name())
        .build();
    }
}    
    

