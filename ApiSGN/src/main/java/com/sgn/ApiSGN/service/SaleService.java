package com.sgn.ApiSGN.service;

import com.sgn.ApiSGN.exception.SaleException;
import com.sgn.ApiSGN.model.Sale;
import com.sgn.ApiSGN.repository.SaleRepository;
import com.sgn.ApiSGN.utils.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

    @Autowired
    SaleRepository saleRepository;

    public void addSale(Sale sale) throws ErrorResponse {
        try {
            this.saleRepository.save(sale);
        } catch (Exception e) {
            throw new ErrorResponse(HttpStatus.CONTINUE.value(), e.getMessage(), LocalDate.now());
        }
    }

    public Sale findSaleById(Long id) throws ErrorResponse {
        try {
            if (id == null) {
                throw new IllegalArgumentException("Wrong id format");
            } else {
                Optional<Sale> optionalSale = saleRepository.findById(id);
                if (optionalSale.isEmpty()) {
                    System.out.println("List is empty");
                    return null;
                } else {
                    return optionalSale.get();
                }
            }
        } catch (Exception e) {
            throw new ErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage(), LocalDate.now());
        }
    }

    public List<Sale> findAllSales() throws ErrorResponse {
        try {
            List<Sale> saleList = saleRepository.findAll();
            if (saleList.isEmpty()) {
                throw new SaleException("List is empty");
            } else {
                return saleList;
            }
        } catch (Exception e) {
            throw new ErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage(), LocalDate.now());
        }
    }

    public void deleteSale(Long id) throws ErrorResponse{
        try{
            if(id == null){
                throw new IllegalArgumentException("Wrong id format");
            } else if (!saleRepository.existsById(id)) {
                throw new SaleException("Sale does not exist");
            }else{
                saleRepository.deleteById(id);
            }
        }catch (Exception e){
            throw new ErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage(), LocalDate.now());
        }
    }
}
