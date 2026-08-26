package SpringBoot_Auth.demo.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import SpringBoot_Auth.demo.Entity.User;

@Repository
public interface UserRepo extends JpaRepository<User,Long>{

    Optional<User> findByEmail(String email);
 
}

