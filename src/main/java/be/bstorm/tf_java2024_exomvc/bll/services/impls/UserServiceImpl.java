package be.bstorm.tf_java2024_exomvc.bll.services.impls;

import be.bstorm.tf_java2024_exomvc.bll.exceptions.user.UserAlreadyExistException;
import be.bstorm.tf_java2024_exomvc.bll.exceptions.user.UserNotFoundException;
import be.bstorm.tf_java2024_exomvc.bll.services.UserService;
import be.bstorm.tf_java2024_exomvc.dal.repositories.UserRepository;
import be.bstorm.tf_java2024_exomvc.domain.entities.User;
import be.bstorm.tf_java2024_exomvc.domain.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public User login(User user) {
        User existingUser = userRepository.findByUsername(user.getUsername()).orElseThrow(
                () -> new UserNotFoundException("User with username " + user.getUsername() + " not found")
        );
        if(!passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
            throw new BadCredentialsException("Wrong password");
        }
        return existingUser;
    }

    @Override
    public void register(User user) {
        if(userRepository.existsByUsername(user.getUsername())) {
            throw new UserAlreadyExistException("User with username " + user.getUsername() + " already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(UserRole.USER);
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("User with username " + username + " not found"));
    }
}
