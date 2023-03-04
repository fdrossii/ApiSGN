package com.sgn.ApiSGN.service;

import com.sgn.ApiSGN.exception.ProductException;
import com.sgn.ApiSGN.model.Product;
import com.sgn.ApiSGN.repository.ProductRepository;
import com.sgn.ApiSGN.utils.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public void addProduct(Product product) throws ErrorResponse {
        try{
            productRepository.save(product);
        }catch (Exception e){
            throw new ErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage(), LocalDate.now());
        }
    }

    public Product findProductById(Long id) throws ErrorResponse{
        try{
            if(id == null){
                throw new IllegalArgumentException("Wrong id format");
            }else{
                Optional<Product> optionalProduct = productRepository.findById(id);
                if(optionalProduct.isEmpty()){
                    throw new ProductException("Product does not exist");
                }else{
                    return optionalProduct.get();
                }
            }
        }catch (Exception e){
            throw new ErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage(), LocalDate.now());
        }
    }

    public List<Product> findAllProducts() throws ErrorResponse{
        try{
            List<Product> productList = productRepository.findAll();
            if (productList.isEmpty()){
                throw new ProductException("List is empty");
            }else{
                return productList;
            }
        }catch (Exception e){
            throw new ErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage(), LocalDate.now());
        }
    }

    public void updateProductStock(Long id, Integer stock) throws ErrorResponse{
        try{
            Optional<Product> productOpt = productRepository.findById(id);
            if(productOpt.isEmpty()){
                throw new ProductException("Product does not exist");

            }else{
                Product product = productOpt.get();
                product.setStock(stock);
                productRepository.save(product);
            }
        }catch (Exception e){
            throw new ErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage(), LocalDate.now());
        }
    }

    public void deleteProduct(Long id) throws ErrorResponse{
        try{
            if(id == null){
                throw new IllegalArgumentException("Wrong id format");
            } else if (!productRepository.existsById(id)) {
                throw new ProductException("Product does not exist");
            }else{
                productRepository.deleteById(id);
            }
        }catch (Exception e){
            throw new ErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage(), LocalDate.now());
        }
    }
}
