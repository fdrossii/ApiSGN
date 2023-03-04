package com.sgn.ApiSGN.model;

import org.springframework.stereotype.Component;


public class Invoice {
    String date;

    SaleItem[] saleItem;

    Double total;

    public Invoice() {
    }

    public Invoice(String date, SaleItem[] saleItem, Double total) {
        this.date = date;
        this.saleItem = saleItem;
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public SaleItem[] getSaleItem() {
        return saleItem;
    }

    public void setSaleItem(SaleItem[] saleItem) {
        this.saleItem = saleItem;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
