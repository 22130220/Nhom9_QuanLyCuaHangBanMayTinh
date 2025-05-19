package vn.hcmuaf.edu.vn.user_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.hcmuaf.edu.vn.user_service.exception.ResourceNotFoundException;
import vn.hcmuaf.edu.vn.user_service.model.Customer;
import vn.hcmuaf.edu.vn.user_service.repository.CustomerRepository;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(Customer customer) {
        // Bạn có thể thêm kiểm tra trùng phone/email nếu muốn, hoặc để database unique constraint xử lý
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy khách hàng với ID: " + id));
    }

    public Customer updateCustomer(Long id, Customer customerDetails) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy khách hàng với ID: " + id));

        existingCustomer.setFullName(customerDetails.getFullName());
        existingCustomer.setPhoneNumber(customerDetails.getPhoneNumber());
        existingCustomer.setEmail(customerDetails.getEmail());
        existingCustomer.setAddress(customerDetails.getAddress());

        return customerRepository.save(existingCustomer);
    }

    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Không tìm thấy khách hàng với ID: " + id);
        }
        customerRepository.deleteById(id);
    }
}
