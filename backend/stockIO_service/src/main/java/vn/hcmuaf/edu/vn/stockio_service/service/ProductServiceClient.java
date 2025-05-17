package vn.hcmuaf.edu.vn.stockio_service.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductServiceClient {

    private final RestTemplate restTemplate;
    private final String productServiceUrl;

    public ProductServiceClient(RestTemplate restTemplate,
                                @Value("${product.service.url}") String productServiceUrl) {
        this.restTemplate = restTemplate;
        this.productServiceUrl = productServiceUrl;
    }

    public List<?> getAllProducts() {
        String url = productServiceUrl + "/api/products";
        Object[] products = restTemplate.getForObject(url, Object[].class);
        return Arrays.asList(products);
    }

}
