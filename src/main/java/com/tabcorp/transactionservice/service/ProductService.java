package com.tabcorp.transactionservice.service;

import com.tabcorp.transactionservice.dto.ProductDto;
import com.tabcorp.transactionservice.model.Product;
import com.tabcorp.transactionservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Create a new product
    public ProductDto createProduct(ProductDto productDto) {
        Product product = new Product(productDto.getProductCode(), productDto.getCost(), productDto.getStatus());
        Product savedProduct = productRepository.save(product);
        return new ProductDto(savedProduct.getProductCode(), savedProduct.getCost(), savedProduct.getStatus());
    }

    // Get a product by productCode
    public Optional<ProductDto> getProductByCode(String productCode) {
        Optional<Product> product = productRepository.findById(productCode);
        return product.map(p -> new ProductDto(p.getProductCode(), p.getCost(), p.getStatus()));
    }

    // Update a product
    public ProductDto updateProduct(String productCode, ProductDto productDto) {
        Optional<Product> existingProduct = productRepository.findById(productCode);
        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();
            product.setCost(productDto.getCost());
            product.setStatus(productDto.getStatus());
            productRepository.save(product);
            return new ProductDto(product.getProductCode(), product.getCost(), product.getStatus());
        }
        return null; // or throw an exception depending on your use case
    }

    // Delete a product
    public void deleteProduct(String productCode) {
        productRepository.deleteById(productCode);
    }

    // Get all products
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> new ProductDto(product.getProductCode(), product.getCost(), product.getStatus()))
                .toList();
    }
}
