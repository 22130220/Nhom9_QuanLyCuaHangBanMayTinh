package vn.hcmuaf.edu.vn.user_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.hcmuaf.edu.vn.user_service.exception.ResourceNotFoundException;
import vn.hcmuaf.edu.vn.user_service.model.Employee;
import vn.hcmuaf.edu.vn.user_service.repository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
    }

    public Employee createEmployee(Employee employee) {
        if (employeeRepository.findByEmail(employee.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee employeeDetails) {
        Employee employee = getEmployeeById(id);

        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmail(employeeDetails.getEmail());
        employee.setPhone(employeeDetails.getPhone());
        employee.setPosition(employeeDetails.getPosition());
        employee.setSalary(employeeDetails.getSalary());
        employee.setHireDate(employeeDetails.getHireDate());
        employee.setUser(employeeDetails.getUser());

        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        Employee employee = getEmployeeById(id);
        employeeRepository.delete(employee);
    }
}
