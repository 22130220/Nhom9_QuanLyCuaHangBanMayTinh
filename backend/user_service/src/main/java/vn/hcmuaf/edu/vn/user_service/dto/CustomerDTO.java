package vn.hcmuaf.edu.vn.user_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class CustomerDTO {
    private Long id;

    @NotBlank(message = "Họ tên không được để trống")
    private String fullName;

    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "^\\d{10,11}$", message = "Số điện thoại gồm 10 hoặc 11 chữ số và ko có chữ cái")
    private String phoneNumber;

    @Email(message = "Email không hợp lệ")
    private String email;

    private String address;

    public CustomerDTO() {
    }

    public CustomerDTO(Long id, String fullName, String phoneNumber, String email, String address) {
        this.id = id;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
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
}
