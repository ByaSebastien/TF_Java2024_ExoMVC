package be.bstorm.tf_java2024_exomvc.bll.services.impls;

import be.bstorm.tf_java2024_exomvc.bll.exceptions.product.ProductAlreadyExistException;
import be.bstorm.tf_java2024_exomvc.bll.exceptions.product.ProductNotFoundException;
import be.bstorm.tf_java2024_exomvc.bll.services.ProductService;
import be.bstorm.tf_java2024_exomvc.bll.specifications.ProductSpecification;
import be.bstorm.tf_java2024_exomvc.dal.repositories.ProductRepository;
import be.bstorm.tf_java2024_exomvc.domain.entities.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCriteria(String name, Integer lowerBound, Integer upperBound) {
        Specification<Product> spec = getSpecification(name, lowerBound, upperBound);
        return productRepository.findAll(spec);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new ProductNotFoundException("Product with id " + id + " not found")
        );
    }

    @Override
    public void addProduct(Product product) {

        if(productRepository.existsByProductName(product.getName())){
            throw new ProductAlreadyExistException("Product with name " + product.getName() + " already exists");
        }
        productRepository.save(product);
    }

    @Override
    public void updateProduct(Long id, Product product) {

        Product existingProduct = productRepository.findById(id).orElseThrow(
                () -> new ProductNotFoundException("Product with id " + id + " not found")
        );
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(Long id) {

        productRepository.deleteById(id);
    }

    private Specification<Product> getSpecification(String name,Integer lowerBound, Integer upperBound) {

        Specification<Product> spec = Specification.where(null);
        if(!name.isBlank()){
            spec = spec.and(ProductSpecification.getByName(name));
        }
        if(lowerBound != null){
            spec = spec.and(ProductSpecification.getByLowerBound(lowerBound));
        }
        if(upperBound != null){
            spec = spec.and(ProductSpecification.getByUpperBound(upperBound));
        }
        return spec;
    }
}
