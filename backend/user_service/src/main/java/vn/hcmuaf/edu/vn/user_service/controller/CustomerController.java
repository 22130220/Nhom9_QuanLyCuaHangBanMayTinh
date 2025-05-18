package vn.hcmuaf.edu.vn.user_service.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.hcmuaf.edu.vn.user_service.dto.CustomerDTO;
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
    public ResponseEntity<ApiResponse> addCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        CustomerDTO savedCustomer = customerService.addCustomer(customerDTO);

        ApiResponse response = new ApiResponse(
                true,
                "Thêm khách hàng thành công",
                savedCustomer
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // API lấy danh sách khách hàng
    @GetMapping
    public ResponseEntity<ApiResponse> getAllCustomers() {
        List<CustomerDTO> customers = customerService.getAllCustomers();

        ApiResponse response = new ApiResponse(
                true,
                "Lấy danh sách khách hàng thành công",
                customers
        );

        return ResponseEntity.ok(response);
    }

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
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateCustomer(
            @PathVariable Long id,
            @Valid @RequestBody CustomerDTO customerDTO) {

        CustomerDTO updatedCustomer = customerService.updateCustomer(id, customerDTO);

        ApiResponse response = new ApiResponse(
                true,
                "Cập nhật thông tin khách hàng thành công",
                updatedCustomer
        );

        return ResponseEntity.ok(response);
    }

    // API xóa khách hàng
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);

        ApiResponse response = new ApiResponse(
                true,
                "Xóa khách hàng thành công",
                null
        );

        return ResponseEntity.ok(response);
    }
}

