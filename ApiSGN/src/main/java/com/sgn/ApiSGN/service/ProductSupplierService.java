package com.sgn.ApiSGN.service;

import com.sgn.ApiSGN.exception.ProductSupplierException;
import com.sgn.ApiSGN.model.ProductSupplier;
import com.sgn.ApiSGN.repository.ProductSupplierRepository;
import com.sgn.ApiSGN.utils.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProductSupplierService {

    @Autowired
    ProductSupplierRepository productSupplierRepository;

    public void addProductSupplier(ProductSupplier productSupplier) throws ErrorResponse {
        try {
            this.productSupplierRepository.save(productSupplier);
        } catch (Exception e) {
            throw new ErrorResponse(HttpStatus.CONTINUE.value(), e.getMessage(), LocalDate.now());
        }
    }

    public ProductSupplier findProductSupplierById(Long id) throws ErrorResponse {
        try {
            if (id == null) {
                throw new IllegalArgumentException("Wrong id format");
            } else {
                Optional<ProductSupplier> optionalProductSupplier = productSupplierRepository.findById(id);
                if (optionalProductSupplier.isEmpty()) {
                    System.out.println("List is empty");
                    return null;
                } else {
                    return optionalProductSupplier.get();
                }
            }
        } catch (Exception e) {
            throw new ErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage(), LocalDate.now());
        }
    }

    public List<ProductSupplier> findAllProductSuppliers() throws ErrorResponse {
        try {
            List<ProductSupplier> productSupplierList = productSupplierRepository.findAll();
            if (productSupplierList.isEmpty()) {
                throw new ProductSupplierException("List is empty");
            } else {
                return productSupplierList;
            }
        } catch (Exception e) {
            throw new ErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage(), LocalDate.now());
        }
    }

    public void updateProductSupplier(ProductSupplier productSupplier) throws ErrorResponse{
        try{
            if(productSupplier.getId() == null){
                throw new ProductSupplierException("Product supplier does not exist");
            }else{
                productSupplierRepository.save(productSupplier);
            }
        }catch (Exception e){
            throw new ErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage(), LocalDate.now());
        }
    }

    public void deleteProductSupplier(Long id) throws ErrorResponse{
        try{
            if(id == null){
                throw new IllegalArgumentException("Wrong id format");
            } else if (!productSupplierRepository.existsById(id)) {
                throw new ProductSupplierException("Sale does not exist");
            }else{
                productSupplierRepository.deleteById(id);
            }
        }catch (Exception e){
            throw new ErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage(), LocalDate.now());
        }
    }
}
