package be.bstorm.tf_java2024_exomvc.pl.controllers;

import be.bstorm.tf_java2024_exomvc.bll.services.ProductService;
import be.bstorm.tf_java2024_exomvc.domain.entities.Product;
import be.bstorm.tf_java2024_exomvc.pl.models.product.forms.ProductForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public String getProducts(Model model) {
        List<Product> products = productService.getProducts();
        model.addAttribute("products", products);
        return "product/index";
    }

    @GetMapping("/{id:^[0-9]+$}")
    public String getProductById(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "product/details";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/create")
    public String createProduct(Model model) {
        model.addAttribute("form", new ProductForm());
        return "product/create";
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/create")
    public String createProduct(
            @ModelAttribute(name = "form") @Valid ProductForm productForm,
            BindingResult bindingResult,
            Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("form", productForm);
            return "product/create";
        }
        productService.addProduct(productForm.toEntity());
        return "redirect:/product";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/update/{id:^[0-9]+$}")
    public String updateProduct(
            @PathVariable Long id,
            Model model
    ) {
        ProductForm form = ProductForm.fromEntity(productService.getProductById(id));
        model.addAttribute("form", form);
        model.addAttribute("productId", id);
        return "product/update";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/update/{id:^[0-9]+$}")
    public String updateProduct(
            @PathVariable Long id,
            @ModelAttribute(name = "form") @Valid ProductForm form,
            BindingResult bindingResult,
            Model model
    ){
        if(bindingResult.hasErrors()) {
            model.addAttribute("form", form);
            model.addAttribute("productId", id);
            return "product/update";
        }
        productService.updateProduct(id, form.toEntity());
        return "redirect:/product";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/delete/{id:^[0-9]+$}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/product";
    }
}
