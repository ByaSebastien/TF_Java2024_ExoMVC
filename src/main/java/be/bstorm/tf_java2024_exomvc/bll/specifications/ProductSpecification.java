package be.bstorm.tf_java2024_exomvc.bll.specifications;


import be.bstorm.tf_java2024_exomvc.domain.entities.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {

    public static Specification<Product> getByName(String name) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.like(root.get("name"), "%" + name + "%");
        });
    }

    public static Specification<Product> getByLowerBound(Integer lowerBound) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.greaterThanOrEqualTo(root.get("price"), lowerBound);
        });
    }

    public static Specification<Product> getByUpperBound(Integer upperBound) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.lessThanOrEqualTo(root.get("price"), upperBound);
        });
    }
}
