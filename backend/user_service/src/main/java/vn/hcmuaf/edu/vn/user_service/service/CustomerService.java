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
        // Kiểm tra số điện thoại đã tồn tại chưa
        if (customerDTO.getPhoneNumber() != null &&
                customerRepository.existsByPhoneNumber(customerDTO.getPhoneNumber())) {
            throw new ResourceAlreadyExistsException("Số điện thoại đã được sử dụng");
        }

        // Kiểm tra email đã tồn tại chưa (nếu có)
        if (customerDTO.getEmail() != null && !customerDTO.getEmail().isEmpty() &&
                customerRepository.existsByEmail(customerDTO.getEmail())) {
            throw new ResourceAlreadyExistsException("Email đã được sử dụng");
        }

        Customer customer = convertToEntity(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return convertToDTO(savedCustomer);
    }

    // Lấy danh sách khách hàng
//    public List<CustomerDTO> getAllCustomers() {
//        return customerRepository.findAll()
//                .stream()
//                .map(this::convertToDTO)
//                .collect(Collectors.toList());
//    }
        public List<Customer> getAllCustomers() {
           return customerRepository.findAll();
    }

    // Lấy thông tin khách hàng theo ID
    public CustomerDTO getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy khách hàng với ID: " + id));
        return convertToDTO(customer);
    }

    // Cập nhật thông tin khách hàng
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy khách hàng với ID: " + id));

        // Kiểm tra số điện thoại đã tồn tại chưa (nếu thay đổi)
        if (customerDTO.getPhoneNumber() != null &&
                !customerDTO.getPhoneNumber().equals(existingCustomer.getPhoneNumber()) &&
                customerRepository.existsByPhoneNumber(customerDTO.getPhoneNumber())) {
            throw new ResourceAlreadyExistsException("Số điện thoại đã được sử dụng");
        }

        // Kiểm tra email đã tồn tại chưa (nếu thay đổi)
        if (customerDTO.getEmail() != null && !customerDTO.getEmail().isEmpty() &&
                !customerDTO.getEmail().equals(existingCustomer.getEmail()) &&
                customerRepository.existsByEmail(customerDTO.getEmail())) {
            throw new ResourceAlreadyExistsException("Email đã được sử dụng");
        }

        existingCustomer.setFullName(customerDTO.getFullName());
        existingCustomer.setPhoneNumber(customerDTO.getPhoneNumber());
        existingCustomer.setEmail(customerDTO.getEmail());
        existingCustomer.setAddress(customerDTO.getAddress());

        Customer updatedCustomer = customerRepository.save(existingCustomer);
        return convertToDTO(updatedCustomer);
    }
}