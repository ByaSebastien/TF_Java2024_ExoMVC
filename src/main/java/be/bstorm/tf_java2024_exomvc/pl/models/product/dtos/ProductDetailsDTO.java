package be.bstorm.tf_java2024_exomvc.pl.models.product.dtos;

import be.bstorm.tf_java2024_exomvc.domain.entities.Product;

public record ProductDetailsDTO(
        Long id,
        String name,
        String description,
        double price
) {

    public static ProductDetailsDTO fromEntity(Product p){
        return new ProductDetailsDTO(p.getId(), p.getName(), p.getDescription(), p.getPrice());
    }
}
