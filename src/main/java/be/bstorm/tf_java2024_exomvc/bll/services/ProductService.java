package be.bstorm.tf_java2024_exomvc.bll.services;

import be.bstorm.tf_java2024_exomvc.domain.entities.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts();
    Product getProductById(Long id);
    void addProduct(Product product);
    void updateProduct(Long id, Product product);
    void deleteProduct(Long id);
}
