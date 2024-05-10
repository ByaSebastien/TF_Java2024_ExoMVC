package be.bstorm.tf_java2024_exomvc.dal.repositories;

import be.bstorm.tf_java2024_exomvc.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long>, JpaSpecificationExecutor<Product> {

    @Query("select p from Product p where p.name like :name")
    Optional<Product> findByProductName(String name);

    @Query("select count(p) > 0 from Product p where p.name like :name")
    boolean existsByProductName(String name);
}
