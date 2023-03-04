package com.sgn.ApiSGN.controller;


import com.sgn.ApiSGN.model.ProductSupplier;
import com.sgn.ApiSGN.service.ProductSupplierService;
import com.sgn.ApiSGN.utils.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class ProductSupplierController {

    @Autowired
    ProductSupplierService productSupplierService;
    @PostMapping("/productSuppliers")
    public ResponseEntity<String> addProductSupplier(@RequestBody ProductSupplier productSupplier) throws ErrorResponse{
        this.productSupplierService.addProductSupplier(productSupplier);
        return new ResponseEntity<>("Product supplier was added", HttpStatus.OK);
    }
    @GetMapping("/productSuppliers/{id}")
    public ResponseEntity<ProductSupplier> findProductSupplierById(@PathVariable Long id) throws ErrorResponse{
        ProductSupplier productSupplier = this.productSupplierService.findProductSupplierById(id);
        return ResponseEntity.ok(productSupplier);
    }
    @GetMapping("/productSuppliers")
    public ResponseEntity<List<ProductSupplier>> findAllProductSuppliers() throws ErrorResponse{
        List<ProductSupplier> productSuppliersList = this.productSupplierService.findAllProductSuppliers();
        return ResponseEntity.ok(productSuppliersList);
    }
    @PutMapping("/productSuppliers")
    public ResponseEntity<String> updateProductSupplier(@RequestBody ProductSupplier productSupplier) throws ErrorResponse{
        this.productSupplierService.updateProductSupplier(productSupplier);
        return new ResponseEntity<>("Product supplier was updated", HttpStatus.OK);
    }
    @DeleteMapping("/productSuppliers/{id}")
    public ResponseEntity<String> deleteProductSupplier(@PathVariable Long id) throws ErrorResponse{
        this.productSupplierService.deleteProductSupplier(id);
        return new ResponseEntity<>("Product supplier was deleted", HttpStatus.OK);
    }
}
