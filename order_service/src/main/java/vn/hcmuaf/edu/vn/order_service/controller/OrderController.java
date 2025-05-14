package vn.hcmuaf.edu.vn.order_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class OrderController {
    @GetMapping
    public String greeting() {
        return "Hello World from Order Service";
    }
}
