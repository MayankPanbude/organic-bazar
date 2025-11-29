package com.organicbazar.product.repository;

import com.organicbazar.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCreatedBy(String createdBy);
    List<Product> findByCreatedBy(String email);
    

}
