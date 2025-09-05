package com.felipelearn.livraria.dto;

public class ApiResponse {
    private boolean isSuccess;
    private Object data;

    public ApiResponse(boolean isSuccess, Object data){
        this.isSuccess = isSuccess;
        this.data = data;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public Object getData() {
        return data;
    }

    
}
