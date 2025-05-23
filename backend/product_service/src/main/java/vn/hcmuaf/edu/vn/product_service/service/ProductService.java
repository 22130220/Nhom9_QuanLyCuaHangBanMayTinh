package vn.hcmuaf.edu.vn.product_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.hcmuaf.edu.vn.product_service.entity.Product;
import vn.hcmuaf.edu.vn.product_service.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(s -> new Product(s.getId(), s.getName()))
                .collect(Collectors.toList());
    }
}