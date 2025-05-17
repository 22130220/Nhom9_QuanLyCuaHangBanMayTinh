package vn.hcmuaf.edu.vn.stockio_service.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.hcmuaf.edu.vn.stockio_service.dto.StockInDTO;
import vn.hcmuaf.edu.vn.stockio_service.service.ProductServiceClient;
import vn.hcmuaf.edu.vn.stockio_service.service.StockInService;
import vn.hcmuaf.edu.vn.stockio_service.service.SupplierServiceClient;

import java.util.List;

@RestController
@RequestMapping("/api/stock-in")
public class StockInController {

    private final StockInService stockInService;
    private final ProductServiceClient productServiceClient;
    private final SupplierServiceClient supplierServiceClient;

    public StockInController(StockInService stockInService, ProductServiceClient productServiceClient, SupplierServiceClient supplierServiceClient) {
        this.stockInService = stockInService;
        this.productServiceClient = productServiceClient;
        this.supplierServiceClient = supplierServiceClient;
    }

    @GetMapping("/products")
    public ResponseEntity<List<?>> getProducts() {
        List<?> products = productServiceClient.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/suppliers")
    public ResponseEntity<List<?>> getSuppliers() {
        List<?> suppliers = supplierServiceClient.getAllSuppliers();
        return ResponseEntity.ok(suppliers);
    }
    @PostMapping
    public ResponseEntity<String> createStockIn(@RequestBody StockInDTO dto) {
        stockInService.createStockIn(dto);
        return ResponseEntity.ok("Nhập kho thành công");
    }
}

