package vn.hcmuaf.edu.vn.product_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.hcmuaf.edu.vn.product_service.entity.Product;
import vn.hcmuaf.edu.vn.product_service.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}