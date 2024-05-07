package be.bstorm.tf_java2024_exomvc.pl.models.product.forms;

import be.bstorm.tf_java2024_exomvc.domain.entities.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductForm {

    @NotBlank
    @Size(min = 2, max = 50)
    private String name;

    @Size(max = 500)
    private String description;

    @NotNull
    @Min(0)
    private Double price;

    public Product toEntity(){
        return new Product(name, description, price);
    }

    public static ProductForm fromEntity(Product product){
        return new ProductForm(product.getName(), product.getDescription(), product.getPrice());
    }
}
