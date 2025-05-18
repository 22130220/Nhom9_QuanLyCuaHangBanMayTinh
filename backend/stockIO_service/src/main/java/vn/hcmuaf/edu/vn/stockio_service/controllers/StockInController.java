package vn.hcmuaf.edu.vn.stockio_service.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.hcmuaf.edu.vn.stockio_service.dto.StockInDTO;
import vn.hcmuaf.edu.vn.stockio_service.service.StockInService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/stock-in")
public class StockInController {

    private final StockInService stockInService;

    public StockInController(StockInService stockInService) {
        this.stockInService = stockInService;
    }

    @PostMapping
    public ResponseEntity<?> createStockIn(@RequestBody StockInDTO dto) {
        try{
            // 13.1.12 StockInController gọi StockInService để tạo mới phiếu nhập kho
            stockInService.createStockIn(dto);
            // 13.1.22 Trả về thông báo "Nhập kho thành công"
            return ResponseEntity.ok("Nhập kho thành công");
        } catch (IllegalArgumentException ex) {
            Map<String, String> error = new HashMap<>();
            error.put("error", ex.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
}

