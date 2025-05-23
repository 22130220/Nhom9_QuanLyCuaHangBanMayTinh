package vn.hcmuaf.edu.vn.stockio_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.hcmuaf.edu.vn.stockio_service.dto.StockInDTO;
import vn.hcmuaf.edu.vn.stockio_service.entity.StockIn;
import vn.hcmuaf.edu.vn.stockio_service.service.StockInService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/stock-in")
public class StockInController {
    @Autowired
    private StockInService stockInService;
    @GetMapping
    public List<StockIn> getAllStockIn(){
        return stockInService.getAllStockIn();
    }


    @PostMapping
    public ResponseEntity<?> createStockIn(@RequestBody StockInDTO dto) {
        try{
            // 13.1.13 StockInController gọi StockInService để tạo mới phiếu nhập kho
            stockInService.createStockIn(dto);
            // 13.1.23 StockInController trả về thông báo "Nhập kho thành công"
            return ResponseEntity.ok("Nhập kho thành công");


            //13.3.2 = 13.4.2  - 13.5.2  – StockInController trả về thông báo và status 400 (Bad request)
        } catch (IllegalArgumentException ex) {
            Map<String, String> error = new HashMap<>();
            error.put("error", ex.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
}
