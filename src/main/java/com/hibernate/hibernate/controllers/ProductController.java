package com.hibernate.hibernate.controllers;

import com.hibernate.hibernate.dao.ProductDao;
import com.hibernate.hibernate.models.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductDao productDao = new ProductDao();

    @PostMapping
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        productDao.saveProduct(product);
        return new ResponseEntity<>("Product added", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        return productDao.getProduct(id)
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productDao.getAllProducts(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productDao.getProduct(id)
                .map(existingProduct -> {
                    existingProduct.setName(product.getName());
                    existingProduct.setDescription(product.getDescription());
                    existingProduct.setPrice(product.getPrice());
                    productDao.updateProduct(existingProduct);
                    return new ResponseEntity<>("Product updated", HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        return productDao.getProduct(id)
                .map(product -> {
                    productDao.deleteProduct(id);
                    return new ResponseEntity<>("Product deleted", HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND));
    }
}
