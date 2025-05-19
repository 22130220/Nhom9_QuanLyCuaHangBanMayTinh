package vn.hcmuaf.edu.vn.user_service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminPageController {

    @GetMapping("/customers")
    public String customersPage() {
        return "admin_manage_customers"; // tên file JSP bỏ phần .jsp
    }
}
