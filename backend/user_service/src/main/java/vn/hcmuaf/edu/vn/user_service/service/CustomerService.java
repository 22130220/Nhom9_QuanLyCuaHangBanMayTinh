package vn.hcmuaf.edu.vn.user_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.hcmuaf.edu.vn.user_service.dto.CustomerDTO;
import vn.hcmuaf.edu.vn.user_service.exception.ResourceAlreadyExistsException;
import vn.hcmuaf.edu.vn.user_service.exception.ResourceNotFoundException;
import vn.hcmuaf.edu.vn.user_service.model.Customer;
import vn.hcmuaf.edu.vn.user_service.repository.CustomerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Chuyển đổi Entity sang DTO
  //  7.1.17 convertToDTO(savedCustomer) - Service chuyển đổi entity thành DTO để trả về
    private CustomerDTO convertToDTO(Customer customer) {
        CustomerDTO dto = new CustomerDTO();
        dto.setId(customer.getId());
        dto.setFullName(customer.getFullName());
        dto.setPhoneNumber(customer.getPhoneNumber());
        dto.setEmail(customer.getEmail());
        dto.setAddress(customer.getAddress());
        return dto;
    }

    // Chuyển đổi DTO sang Entity
    private Customer convertToEntity(CustomerDTO dto) {
        Customer customer = new Customer();
        customer.setId(dto.getId());
        customer.setFullName(dto.getFullName());
        customer.setPhoneNumber(dto.getPhoneNumber());
        customer.setEmail(dto.getEmail());
        customer.setAddress(dto.getAddress());
        return customer;
    }

    // Thêm khách hàng mới
    public CustomerDTO addCustomer(CustomerDTO customerDTO) {
        //  Kiểm tra số điện thoại đã tồn tại chưa
        if (customerDTO.getPhoneNumber() != null &&
                customerRepository.existsByPhoneNumber(customerDTO.getPhoneNumber())) {
            // 7.3.1 throw ResourceAlreadyExistsException("Số điện thoại đã được sử dụng")
            //   7.3.2 409 Conflict (số điện thoại đã tồn tại) - Hệ thống trả về lỗi 409 Conflict
            throw new ResourceAlreadyExistsException("Số điện thoại đã được sử dụng");
        }

        //  Kiểm tra email đã tồn tại chưa (nếu có)
        if (customerDTO.getEmail() != null && !customerDTO.getEmail().isEmpty() &&
                customerRepository.existsByEmail(customerDTO.getEmail())) {
            // 7.4.1 throw ResourceAlreadyExistsException("Email đã được sử dụng")
          // 7.4.2 409 Conflict (email đã tồn tại) - Hệ thống trả về lỗi 409 Conflict
            throw new ResourceAlreadyExistsException("Email đã được sử dụng");
        }

        // 7.1.11 Convert DTO sang Entity Customer
        Customer customer = convertToEntity(customerDTO);
        // 7.1.13 Lưu Customer vào database
        Customer savedCustomer = customerRepository.save(customer);
       // 7.1.14 INSERT INTO customers (...) - Repository thực hiện câu lệnh INSERT vào database
        // 7.1.15 Trả về Customer entity có id (đã lưu thành công)
        // 7.1.16 saved Customer - Repository trả về customer đã lưu cho service
        return convertToDTO(savedCustomer);
        // 7.1.17 convertToDTO(savedCustomer) - Service chuyển đổi entity thành DTO để trả về
    }

    // Lấy danh sách khách hàng
//    public List<CustomerDTO> getAllCustomers() {
//        return customerRepository.findAll()
//                .stream()
//                .map(this::convertToDTO)
//                .collect(Collectors.toList());
//    }

    public List<Customer> getAllCustomers() {
        //  Lấy danh sách khách hàng từ repository
        return customerRepository.findAll();
    }

    // Lấy thông tin khách hàng theo ID
    public CustomerDTO getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy khách hàng với ID: " + id));
        return convertToDTO(customer);
    }

    // Cập nhật thông tin khách hàng
//    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
//        Customer existingCustomer = customerRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy khách hàng với ID: " + id));
//
//        // Kiểm tra số điện thoại đã tồn tại chưa (nếu thay đổi)
//        if (customerDTO.getPhoneNumber() != null &&
//                !customerDTO.getPhoneNumber().equals(existingCustomer.getPhoneNumber()) &&
//                customerRepository.existsByPhoneNumber(customerDTO.getPhoneNumber())) {
//            throw new ResourceAlreadyExistsException("Số điện thoại đã được sử dụng");
//        }
//
//        // Kiểm tra email đã tồn tại chưa (nếu thay đổi)
//        if (customerDTO.getEmail() != null && !customerDTO.getEmail().isEmpty() &&
//                !customerDTO.getEmail().equals(existingCustomer.getEmail()) &&
//                customerRepository.existsByEmail(customerDTO.getEmail())) {
//            throw new ResourceAlreadyExistsException("Email đã được sử dụng");
//        }
//
//        existingCustomer.setFullName(customerDTO.getFullName());
//        existingCustomer.setPhoneNumber(customerDTO.getPhoneNumber());
//        existingCustomer.setEmail(customerDTO.getEmail());
//        existingCustomer.setAddress(customerDTO.getAddress());
//
//        Customer updatedCustomer = customerRepository.save(existingCustomer);
//        return convertToDTO(updatedCustomer);
//    }
}
