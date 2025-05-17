package vn.hcmuaf.edu.vn.stockio_service.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class SupplierServiceClient {

    private final RestTemplate restTemplate;
    private final String supplierServiceUrl;

    // Constructor injection RestTemplate và URL service từ application.properties
    public SupplierServiceClient(RestTemplate restTemplate,
                                 @Value("${supplier.service.url}") String supplierServiceUrl) {
        this.restTemplate = restTemplate;
        this.supplierServiceUrl = supplierServiceUrl;
    }

    // Lấy danh sách nhà cung cấp
    public List<?> getAllSuppliers() {
        String url = supplierServiceUrl + "/api/suppliers";
        Object[] suppliers = restTemplate.getForObject(url, Object[].class);
        return Arrays.asList(suppliers);
    }
}
