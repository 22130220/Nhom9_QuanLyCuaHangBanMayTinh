package vn.hcmuaf.edu.vn.stockio_service.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
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

    private final StockInRepository stockInRepository;
    private final RestTemplate restTemplate;
    private final String productServiceUrl;

    public StockInService(StockInRepository stockInRepository,
                          RestTemplate restTemplate,
                          @Value("${product.service.url}") String productServiceUrl) {
        this.stockInRepository = stockInRepository;
        this.restTemplate = restTemplate;
        this.productServiceUrl = productServiceUrl;
    }

    @Transactional
    public void createStockIn(StockInDTO dto) {
        // 13.1.13 - Kiểm tra dữ liệu hợp lệ
        if (dto.getSupplierId() == null || dto.getCreatedDate() == null) {
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

        // 13.1.14 - Tạo mới StockIn
        StockIn stockIn = new StockIn();
        stockIn.setSupplierId(dto.getSupplierId());
        stockIn.setCreatedDate(dto.getCreatedDate());
        stockIn.setNote(dto.getNote());

        // 13.1.15 & 13.1.18 - Tạo và gán StockInItem
        List<StockInItem> items = new ArrayList<>();
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (StockInItemDTO itemDTO : dto.getItems()) {
            StockInItem item = new StockInItem();
            item.setProductId(itemDTO.getProductId());
            item.setQuantity(itemDTO.getQuantity());
            item.setUnitPrice(itemDTO.getUnitPrice());
            item.setNote(itemDTO.getNote());
            item.setStockIn(stockIn);
            items.add(item);

            // 13.1.16 - Tính tổng tiền
            BigDecimal itemTotal = itemDTO.getUnitPrice().multiply(BigDecimal.valueOf(itemDTO.getQuantity()));
            totalAmount = totalAmount.add(itemTotal);
        }
        stockIn.setItems(items);

        // 13.1.17 - Lưu tổng tiền vào StockIn
        stockIn.setTotalAmount(totalAmount);

        // 13.1.19 - Lưu StockIn vào DB
        stockInRepository.save(stockIn);

        // 13.1.20 - Gọi product-service cập nhật tồn kho
        for (StockInItem item : items) {
            String url = productServiceUrl + "/api/products/" + item.getProductId() + "/stock";
            StockUpdateRequest request = new StockUpdateRequest(item.getQuantity());
            restTemplate.put(url, request);
        }
    }

    // Class cho request cập nhật tồn kho
    public static class StockUpdateRequest {
        private int quantity;

        public StockUpdateRequest(int quantity) {
            this.quantity = quantity;
        }

        public int getQuantity() { return quantity; }
        public void setQuantity(int quantity) { this.quantity = quantity; }
    }
}

