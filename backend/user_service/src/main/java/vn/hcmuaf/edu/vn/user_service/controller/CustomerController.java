package vn.hcmuaf.edu.vn.user_service.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.hcmuaf.edu.vn.user_service.dto.ApiResponse;
import vn.hcmuaf.edu.vn.user_service.dto.CustomerDTO;
import vn.hcmuaf.edu.vn.user_service.model.Customer;
import vn.hcmuaf.edu.vn.user_service.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // API thêm khách hàng
    @PostMapping
    // 7.1.0 Nhận POST /api/customers với CustomerDTO
    // 7.1.1  @PostMapping addCustomer(@Valid CustomerDTO)  @Valid kiểm tra NotBlank, Email, Pattern
    // 7.1.2 Gọi service xử lý nghiệp vụ thêm khách hàng
    public ResponseEntity<ApiResponse> addCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        CustomerDTO savedCustomer = customerService.addCustomer(customerDTO);
        ApiResponse response = new ApiResponse(
            //    7.1.19 Tạo ApiResponse(success=true, message, data=CustomerDTO) - Service tạo response thành công
                true,
                "Thêm khách hàng thành công",
                savedCustomer
        );
        // 7.1.20 Tạo ApiResponse trả về client thông báo thành công
       // 7.1.20 Hiển thị alert "Thêm khách hàng thành công" Đóng modal, load lại danh sách - Giao diện người dùng hiển thị thông báo thành công và cập nhật danh sách
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Lấy danh sách khách hàng
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

//    @GetMapping
//    public ResponseEntity<ApiResponse> getAllCustomers() {
//        List<CustomerDTO> customers = customerService.getAllCustomers();
//
//        ApiResponse response = new ApiResponse(
//                true,
//                "Lấy danh sách khách hàng thành công",
//                customers
//        );
//
//        return ResponseEntity.ok(response);
//    }

    // API lấy thông tin khách hàng theo ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getCustomerById(@PathVariable Long id) {
        CustomerDTO customer = customerService.getCustomerById(id);

        ApiResponse response = new ApiResponse(
                true,
                "Lấy thông tin khách hàng thành công",
                customer
        );

        return ResponseEntity.ok(response);
    }

    // API cập nhật thông tin khách hàng
//    @PutMapping("/{id}")
//    public ResponseEntity<ApiResponse> updateCustomer(
//            @PathVariable Long id,
//            @Valid @RequestBody CustomerDTO customerDTO) {
//
//        CustomerDTO updatedCustomer = customerService.updateCustomer(id, customerDTO);
//
//        ApiResponse response = new ApiResponse(
//                true,
//                "Cập nhật thông tin khách hàng thành công",
//                updatedCustomer
//        );
//
//        return ResponseEntity.ok(response);
//    }
}
