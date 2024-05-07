package be.bstorm.tf_java2024_exomvc.pl.controllers.security;

import be.bstorm.tf_java2024_exomvc.bll.services.UserService;
import be.bstorm.tf_java2024_exomvc.domain.entities.User;
import be.bstorm.tf_java2024_exomvc.pl.models.user.LoginForm;
import be.bstorm.tf_java2024_exomvc.pl.models.user.RegisterForm;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;


    @PreAuthorize("isAnonymous()")
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("form", new LoginForm());
        return "auth/login";
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/login")
    public String loginPost(
            @ModelAttribute("form") @Valid LoginForm form,
            BindingResult bindingResult,
            Model model,
            HttpSession session
    ) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("form", form);
            return "auth/login";
        }
        User currentUser = userService.login(form.toEntity());
        session.setAttribute("currentUser",currentUser.getUsername());
        session.setAttribute("role", currentUser.getRole().toString());
        return "redirect:/";
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("form", new RegisterForm());
        return "auth/register";
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/register")
    public String registerPost(
            @ModelAttribute("form") @Valid RegisterForm form,
            BindingResult bindingResult,
            Model model,
            HttpSession session
    ) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("form", form);
            return "auth/register";
        }
        userService.register(form.toEntity());
        return "redirect:/auth/login";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("currentUser");
        session.removeAttribute("role");
        session.invalidate();
        return "redirect:/";
    }
}
