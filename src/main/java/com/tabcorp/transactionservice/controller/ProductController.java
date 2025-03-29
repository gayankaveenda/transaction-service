package com.tabcorp.transactionservice.controller;

import com.tabcorp.transactionservice.dto.ProductDto;
import com.tabcorp.transactionservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@Validated
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Create a new product
    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        ProductDto createdProduct = productService.createProduct(productDto);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    // Get product by productCode
    @GetMapping("/{productCode}")
    public ResponseEntity<ProductDto> getProductByCode(@PathVariable String productCode) {
        Optional<ProductDto> product = productService.getProductByCode(productCode);
        return product.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Get all products
    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // Update product by productCode
    @PutMapping("/{productCode}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable String productCode, @RequestBody ProductDto productDto) {
        ProductDto updatedProduct = productService.updateProduct(productCode, productDto);
        return updatedProduct != null
                ? new ResponseEntity<>(updatedProduct, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Delete product by productCode
    @DeleteMapping("/{productCode}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String productCode) {
        productService.deleteProduct(productCode);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
