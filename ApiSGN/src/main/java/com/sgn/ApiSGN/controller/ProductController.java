package com.sgn.ApiSGN.controller;

import com.sgn.ApiSGN.model.Product;
import com.sgn.ApiSGN.service.ProductService;
import com.sgn.ApiSGN.utils.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService productService;


    @PostMapping("/products")
    public ResponseEntity<String> addProduct(@RequestBody Product product) throws ErrorResponse {
        productService.addProduct(product);
        return new ResponseEntity<>("Product was added", HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable Long id) throws ErrorResponse{
        Product product = productService.findProductById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> findAllProducts() throws ErrorResponse{
        List<Product> products = this.productService.findAllProducts();
        return ResponseEntity.ok(products);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<String> updateProductStock(@PathVariable Long id,@RequestBody Integer stock) throws ErrorResponse{
        this.productService.updateProductStock(id, stock);
        return new ResponseEntity<>("Product was updated", HttpStatus.OK);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) throws ErrorResponse{
        this.productService.deleteProduct(id);
        return new ResponseEntity<>("Product was deleted", HttpStatus.OK);
    }



}