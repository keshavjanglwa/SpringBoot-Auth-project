package SpringBoot_Auth.demo.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private String fullname;
    private String email;
    private String password;
    private LocalDate evenDate;
    private String token;
    private LocalDateTime restTokenExpiry;
    
    @Enumerated(EnumType.STRING)
    private Role role ;


    
}


