package be.bstorm.tf_java2024_exomvc.pl.models.product.dtos;

import be.bstorm.tf_java2024_exomvc.domain.entities.Product;

public record ProductShortDTO(
        Long id,
        String name,
        double price
) {

    public static ProductShortDTO fromEntity(Product p) {
        return new ProductShortDTO(p.getId(), p.getName(), p.getPrice());
    }
}
