package com.omsai.productservice.controller;

import com.omsai.productservice.dto.ProductRequest;
import com.omsai.productservice.dto.ProductResponse;
import com.omsai.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("allProducts")
    public List<ProductResponse> findAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void creatProduct(@RequestBody ProductRequest productRequest) {
        productService.save(productRequest);
    }
}
