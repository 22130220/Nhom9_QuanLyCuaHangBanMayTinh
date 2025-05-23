package vn.hcmuaf.edu.vn.stockio_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.hcmuaf.edu.vn.stockio_service.entity.StockIn;

import java.util.Optional;

@Repository
public interface StockInRepository extends JpaRepository<StockIn, Long> {
}

