package com.amorales.vaccination.exception;

import com.amorales.vaccination.pojos.errors.ApiError;

public class GeneralException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private ApiError apiError;

    public GeneralException(ApiError apiError){
        this.apiError = apiError;
    }

    public ApiError getApiError(){
        return this.apiError;
    }
}
