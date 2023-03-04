package com.sgn.ApiSGN.model;

import org.springframework.stereotype.Component;

public class SaleItem {
    private String name;
    private Double price;
    private Integer units;
    private Double total;

    public SaleItem() {
    }

    public SaleItem(String name, Double price, Integer units, Double total) {
        this.name = name;
        this.price = price;
        this.units = units;
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getUnits() {
        return units;
    }

    public void setUnits(Integer units) {
        this.units = units;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
