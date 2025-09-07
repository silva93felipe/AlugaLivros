package com.felipelearn.livraria.dto;

import java.util.Map;

public class ApiResponse {
    private boolean isSuccess;
    private Object data;
    private Map<String, String> errors;

    public ApiResponse(boolean isSuccess, Object data,  Map<String, String> errors){
        this.isSuccess = isSuccess;
        this.data = data;
        this.errors = errors;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public Object getData() {
        return data;
    }

    public Map<String, String> getErrors(){
        return errors;
    }

    
}
