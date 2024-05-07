package be.bstorm.tf_java2024_exomvc.il.utils;

import be.bstorm.tf_java2024_exomvc.dal.repositories.ProductRepository;
import be.bstorm.tf_java2024_exomvc.dal.repositories.UserRepository;
import be.bstorm.tf_java2024_exomvc.domain.entities.Product;
import be.bstorm.tf_java2024_exomvc.domain.entities.User;
import be.bstorm.tf_java2024_exomvc.domain.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {

        if (userRepository.count() == 0) {

            User admin = new User("Admin", passwordEncoder.encode("Test1234="), UserRole.ADMIN);

            userRepository.save(admin);
        }

        if(productRepository.count() == 0) {
            List<Product> products = List.of(
                    new Product("Pomme","Pomme verte delicieuse",5.0),
                    new Product("Poire","Superbe poire bio",6.5),
                    new Product("Banane","De belles bananes jaunes",12.5)
            );

            for (Product product : products) {
                productRepository.save(product);
            }
        }
    }
}
