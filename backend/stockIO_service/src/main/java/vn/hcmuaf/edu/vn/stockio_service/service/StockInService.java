package vn.hcmuaf.edu.vn.stockio_service.service;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.hcmuaf.edu.vn.stockio_service.dto.StockInDTO;
import vn.hcmuaf.edu.vn.stockio_service.dto.StockInItemDTO;
import vn.hcmuaf.edu.vn.stockio_service.entity.StockIn;
import vn.hcmuaf.edu.vn.stockio_service.entity.StockInItem;
import vn.hcmuaf.edu.vn.stockio_service.repository.StockInRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class StockInService {
    @Autowired
    private StockInRepository stockInRepository;
    @Autowired
    private InventoryService inventoryService;

    @Transactional
    public void createStockIn(StockInDTO dto) {
        // 13.1.14 - Kiểm tra dữ liệu hợp lệ
        if (dto.getSupplierId() == 0 || dto.getCreatedDate() == null) {
            throw new IllegalArgumentException("Nhà cung cấp và ngày nhập không được để trống");
        }
        if (dto.getItems() == null || dto.getItems().isEmpty()) {
            throw new IllegalArgumentException("Phải có ít nhất một sản phẩm");
        }
        for (StockInItemDTO item : dto.getItems()) {
            if (item.getQuantity() == null || item.getQuantity() <= 0)
                throw new IllegalArgumentException("Số lượng phải lớn hơn 0");
            if (item.getUnitPrice() == null || item.getUnitPrice().compareTo(BigDecimal.ZERO) <= 0)
                throw new IllegalArgumentException("Đơn giá phải lớn hơn 0");
        }

        // 13.1.15 - Tạo mới StockIn
        StockIn stockIn = new StockIn();
        stockIn.setCreater_id(dto.getCreaterID());
        stockIn.setSupplier_id(dto.getSupplierId());
        stockIn.setCreated_date(dto.getCreatedDate());
        stockIn.setNote(dto.getNote());

        // 13.1.16 - Tạo các StockInItem
        List<StockInItem> items = new ArrayList<>();
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (StockInItemDTO itemDTO : dto.getItems()) {
            StockInItem item = new StockInItem();
            item.setProductId(itemDTO.getProductId());
            item.setQuantity(itemDTO.getQuantity());
            item.setUnit_price(itemDTO.getUnitPrice());
            item.setNote(itemDTO.getNote());
            item.setStockIn(stockIn);
            items.add(item);

            // 13.1.17 - Tính tổng tiền
            BigDecimal itemTotal = itemDTO.getUnitPrice().multiply(BigDecimal.valueOf(itemDTO.getQuantity()));
            totalAmount = totalAmount.add(itemTotal);
        }
        // 13.1.18 - Lưu tổng tiền vào StockIn
        stockIn.setTotal_amount(totalAmount);

        // 13.1.19 - Gán danh sách StockInItem vào StockIn
        stockIn.setItems(items);

        // 13.1.20 - Lưu StockIn vào DB
        stockInRepository.save(stockIn);

        // 13.1.21 - Gọi InventoryService để cập nhật tồn kho
        for (StockInItem item : items) {
            inventoryService.increaseQuantity(item.getProductId(), item.getQuantity());
        }

    }

    public List<StockIn> getAllStockIn() {
        return stockInRepository.findAll();
    }
}

