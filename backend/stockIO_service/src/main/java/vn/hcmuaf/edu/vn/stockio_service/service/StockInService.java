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
        // 13.1.18 - Kiểm tra dữ liệu hợp lệ
        if (dto.getItems() == null || dto.getItems().isEmpty()) {
            //13.3.0 – Khi không có sản phẩm nhập kho.
            //13.3.1 – StockInService trả về “Phải có ít nhất một sản phẩm”
            throw new IllegalArgumentException("Phải có ít nhất một sản phẩm");
        }
        for (StockInItemDTO item : dto.getItems()) {
            if (item.getQuantity() == null || item.getQuantity() <= 0)
                //13.4.0 - Khi nhập số lượng nhỏ hơn 0:
                //13.4.1 - StockInService trả về “Số lượng phải lớn hơn 0”.
                throw new IllegalArgumentException("Số lượng phải lớn hơn 0");
            if (item.getUnitPrice() == null || item.getUnitPrice().compareTo(BigDecimal.ZERO) <= 0)
                //13.5.0 - Khi nhập đơn giá nhỏ hơn 0:
                //13.5.1 - StockInService trả về “Đơn giá phải lớn hơn 0”.
                throw new IllegalArgumentException("Đơn giá phải lớn hơn 0");
        }

        // 13.1.19 - Tạo mới StockIn
        StockIn stockIn = new StockIn();
        stockIn.setCreater_id(dto.getCreaterID());
        stockIn.setSupplier_id(dto.getSupplierId());
        stockIn.setCreated_date(dto.getCreatedDate());
        stockIn.setNote(dto.getNote());

        // 13.1.20 - Tạo các StockInItem
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

            // 13.1.21 - Tính tổng tiền
            BigDecimal itemTotal = itemDTO.getUnitPrice().multiply(BigDecimal.valueOf(itemDTO.getQuantity()));
            totalAmount = totalAmount.add(itemTotal);
        }
        // 13.1.22 - Lưu tổng tiền vào StockIn
        stockIn.setTotal_amount(totalAmount);

        // 13.1.23 - Gán danh sách StockInItem vào StockIn
        stockIn.setItems(items);

        // 13.1.24 - Lưu StockIn vào DB
        stockInRepository.save(stockIn);

        // 13.1.25 - Gọi InventoryService để cập nhật tồn kho
        for (StockInItem item : items) {
            inventoryService.increaseQuantity(item.getProductId(), item.getQuantity());
        }

    }

    public List<StockIn> getAllStockIn() {
        return stockInRepository.findAll();
    }
}

