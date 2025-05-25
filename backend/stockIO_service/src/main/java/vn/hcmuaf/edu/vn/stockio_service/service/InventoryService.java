package vn.hcmuaf.edu.vn.stockio_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.hcmuaf.edu.vn.stockio_service.entity.Inventory;
import vn.hcmuaf.edu.vn.stockio_service.repository.InventoryRepository;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;
    // 13.1.26 - Gọi InventoryRepository để cập nhật tồn kho trong cơ sở dữ liệu
    public void increaseQuantity(Long productId, int quantity) {
        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseGet(() -> new Inventory(productId, 0));
        inventory.setQuantity(inventory.getQuantity() + quantity);
        inventoryRepository.save(inventory);
    }
}
