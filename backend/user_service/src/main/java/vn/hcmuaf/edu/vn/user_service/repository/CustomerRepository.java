package vn.hcmuaf.edu.vn.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.hcmuaf.edu.vn.user_service.model.Customer;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // 7.1.4 existsByPhoneNumber(phoneNumber) (Kiểm tra số điện thoại trong DB)
    // 7.1.5 SELECT COUNT(*) > 0 FROM customers WHERE phone_number = ? (Truy vấn SQL)
    boolean existsByPhoneNumber(String phoneNumber);

    // 7.1.10 existsByEmail(email) (Kiểm tra email)
    // 7.1.11 SELECT COUNT(*) > 0 FROM customers WHERE email = ? (Truy vấn SQL)
    boolean existsByEmail(String email);

    Optional<Customer> findByPhoneNumber(String phoneNumber);

    Optional<Customer> findByEmail(String email);
}
