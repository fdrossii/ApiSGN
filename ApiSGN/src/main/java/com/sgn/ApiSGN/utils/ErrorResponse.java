package com.sgn.ApiSGN.utils;

import java.time.LocalDate;

public class ErrorResponse extends Throwable{

    private Integer status;
    private String message;
    private LocalDate date;

    public ErrorResponse(){

    }
    public ErrorResponse(Integer status, String message, LocalDate date){
        this.status = status;
        this.message = message;
        this.date = date;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
