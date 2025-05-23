package vn.hcmuaf.edu.vn.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.hcmuaf.edu.vn.user_service.model.Customer;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // 7.1.3 existsByPhoneNumber(phoneNumber) (Kiểm tra số điện thoại trong DB)
    // 7.1.4 SELECT COUNT(*) > 0 FROM customers WHERE phone_number = ? (Truy vấn SQL)
    // 7.1.5 result (true/false) - Database trả về kết quả kiểm tra
    boolean existsByPhoneNumber(String phoneNumber);

    // 7.1.7 existsByEmail(email) (Kiểm tra email)
    // 7.1.8 SELECT COUNT(*) > 0 FROM customers WHERE email = ? (Truy vấn SQL)
    // 7.1.9 result (true/false) - Database trả về kết quả kiểm tra email
    boolean existsByEmail(String email);

    Optional<Customer> findByPhoneNumber(String phoneNumber);

    Optional<Customer> findByEmail(String email);
}
