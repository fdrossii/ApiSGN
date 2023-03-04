package com.sgn.ApiSGN.controller;


import com.sgn.ApiSGN.model.Invoice;
import com.sgn.ApiSGN.model.Sale;
import com.sgn.ApiSGN.service.InvoicePdfService;
import com.sgn.ApiSGN.service.SaleService;
import com.sgn.ApiSGN.utils.ErrorResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;



@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class SaleController {

    @Autowired
    SaleService saleService;
    @Autowired
    InvoicePdfService invoicePdfService;


    @PostMapping("/sales")
    public ResponseEntity<String> addSale(@RequestBody Sale sale) throws ErrorResponse {
        saleService.addSale(sale);
        return new ResponseEntity<>("Sale was added", HttpStatus.OK);
    }
    @GetMapping("/sales/{id}")
    public ResponseEntity<Sale> findSaleById(@PathVariable Long id) throws ErrorResponse{
        Sale sale = saleService.findSaleById(id);
        return ResponseEntity.ok(sale);
    }
    @GetMapping("/sales")
    public ResponseEntity<List<Sale>> findAllSales() throws ErrorResponse{
        List<Sale> saleList = this.saleService.findAllSales();
        return ResponseEntity.ok(saleList);
    }
    @DeleteMapping("/sales/{id}")
    public ResponseEntity<String> deleteSale(@PathVariable Long id) throws ErrorResponse{
        this.saleService.deleteSale(id);
        return new ResponseEntity<>("Sale was deleted", HttpStatus.OK);
    }

    @PostMapping("/sales/pdf")
    public void downloadInvoicePDF(HttpServletResponse response, @RequestBody Invoice invoice){
        try{
            Path file = Paths.get(InvoicePdfService.generateInvoicePdf(invoice).getAbsolutePath());
            if (Files.exists(file)){
                response.setContentType("application/pdf");
                response.addHeader("Content-Disposition", "attachment; filename"+ file.getFileName());
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            }
        } catch (Exception | ErrorResponse e){
            e.printStackTrace();
        }
    }
}
