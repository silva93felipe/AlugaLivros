package com.felipelearn.livraria.dto;

public class ApiResponse {
    private int statusCode;
    private boolean isSuccess;
    private Object data;

    public ApiResponse(int statusCode, boolean isSuccess, Object data){
        this.statusCode = statusCode;
        this.isSuccess = isSuccess;
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public Object getData() {
        return data;
    }

    
}
