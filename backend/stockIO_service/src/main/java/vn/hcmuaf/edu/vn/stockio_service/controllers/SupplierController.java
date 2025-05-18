package vn.hcmuaf.edu.vn.stockio_service.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.hcmuaf.edu.vn.stockio_service.entity.Supplier;
import vn.hcmuaf.edu.vn.stockio_service.service.SupplierService;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {
    private SupplierService supplierService;
    @GetMapping
    public List<Supplier> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }
}
