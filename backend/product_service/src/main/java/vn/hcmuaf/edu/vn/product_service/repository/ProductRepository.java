package vn.hcmuaf.edu.vn.product_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.hcmuaf.edu.vn.product_service.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

