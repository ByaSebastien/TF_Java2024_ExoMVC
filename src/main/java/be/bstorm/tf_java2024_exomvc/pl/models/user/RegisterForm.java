package be.bstorm.tf_java2024_exomvc.pl.models.user;

import be.bstorm.tf_java2024_exomvc.domain.entities.User;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterForm {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public User toEntity() {
        return new User(this.getUsername(), this.getPassword());
    }
}
