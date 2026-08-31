package SpringBoot_Auth.demo.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import SpringBoot_Auth.demo.Entity.User;

@Repository
public interface UserRepo extends JpaRepository<User,Long>{

    User findByEmail(String email);

    User findByToken(String token);

    boolean existsByEmail(String email);
 
}

