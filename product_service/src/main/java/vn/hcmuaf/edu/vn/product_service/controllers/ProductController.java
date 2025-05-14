package vn.hcmuaf.edu.vn.product_service.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class ProductController {

    @GetMapping
    public String greeting() {
        return "Hello World from ProductController";
    }
}
