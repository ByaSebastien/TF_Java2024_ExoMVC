package be.bstorm.tf_java2024_exomvc.bll.services;

import be.bstorm.tf_java2024_exomvc.domain.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User login(User user);

    void register(User user);
}
