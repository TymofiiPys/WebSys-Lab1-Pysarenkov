package org.example.websyslab1_proj.controller;

import org.example.websyslab1_proj.dto.ProductDTO;
import org.example.websyslab1_proj.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(productService.findProductById(id)
                .map(p -> new ProductDTO(p.getName()))
                .orElseGet(() -> new ProductDTO("N/A")));
    }
}
