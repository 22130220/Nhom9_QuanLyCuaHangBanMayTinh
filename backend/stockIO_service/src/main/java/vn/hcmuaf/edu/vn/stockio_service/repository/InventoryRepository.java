package vn.hcmuaf.edu.vn.stockio_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.hcmuaf.edu.vn.stockio_service.entity.Inventory;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findByProductId(Long productId);
}


