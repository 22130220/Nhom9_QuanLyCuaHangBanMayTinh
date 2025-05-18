package vn.hcmuaf.edu.vn.stockio_service.service;

import org.springframework.stereotype.Service;
import vn.hcmuaf.edu.vn.stockio_service.entity.Supplier;
import vn.hcmuaf.edu.vn.stockio_service.repository.SupplierRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierService {
    private SupplierRepository supplierRepository;

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll()
                .stream()
                .map(s -> new Supplier(s.getId(), s.getName()))
                .collect(Collectors.toList());
    }
}

