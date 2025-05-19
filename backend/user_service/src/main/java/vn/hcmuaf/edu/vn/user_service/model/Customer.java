package vn.hcmuaf.edu.vn.user_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "customers")
@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 7.1.1 Validate input (@Valid)  @Valid kiểm tra NotBlank, Email, Pattern
    @NotBlank(message = "Họ tên không được để trống")
    @Column(nullable = false)
    private String fullName;

    // 7.1.1 Validate input (@Valid)  @Valid kiểm tra NotBlank, Email, Pattern
    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "^\\d{10,11}$", message = "Số điện thoại không hợp lệ")
    @Column(nullable = false, unique = true)
    private String phoneNumber;

    // 7.1.1 Validate input (@Valid)  @Valid kiểm tra NotBlank, Email, Pattern
    @Email(message = "Email không hợp lệ")
    @Column(unique = true)
    private String email;

    private String address;

    @Column(name = "created_at")

    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // 7.1.17 Create new Customer
    public Customer() {}

    public Customer(Long id, String fullName, String phoneNumber, String email, String address, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Họ tên không được để trống") String getFullName() {
        return fullName;
    }

    public void setFullName(@NotBlank(message = "Họ tên không được để trống") String fullName) {
        this.fullName = fullName;
    }

    public @NotBlank(message = "Số điện thoại không được để trống") @Pattern(regexp = "^\\d{10,11}$", message = "Số điện thoại không hợp lệ") String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@NotBlank(message = "Số điện thoại không được để trống") @Pattern(regexp = "^\\d{10,11}$", message = "Số điện thoại không hợp lệ") String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public @Email(message = "Email không hợp lệ") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "Email không hợp lệ") String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
