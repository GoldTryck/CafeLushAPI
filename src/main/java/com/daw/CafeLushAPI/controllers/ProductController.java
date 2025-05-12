package com.daw.CafeLushAPI.controllers;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import com.daw.CafeLushAPI.dtos.request.ProductRequest;
import com.daw.CafeLushAPI.dtos.response.ProductResponse;
import com.daw.CafeLushAPI.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
        return ResponseEntity.ok(productService.findAll());
    }

}
