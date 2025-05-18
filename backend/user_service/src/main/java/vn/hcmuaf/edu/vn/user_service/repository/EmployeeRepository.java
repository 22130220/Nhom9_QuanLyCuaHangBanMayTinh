package vn.hcmuaf.edu.vn.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.hcmuaf.edu.vn.user_service.model.Employee;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmail(String email);
}
