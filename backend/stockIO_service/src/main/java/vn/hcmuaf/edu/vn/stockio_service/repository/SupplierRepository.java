package vn.hcmuaf.edu.vn.stockio_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.hcmuaf.edu.vn.stockio_service.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}

