package be.bstorm.tf_java2024_exomvc.pl.models.product.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductFilter {

    private String name;
    private Integer lowerBound;
    private Integer upperBound;
}
