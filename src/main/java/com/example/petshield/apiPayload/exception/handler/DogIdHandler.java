package com.example.petshield.apiPayload.exception.handler;

import com.example.petshield.apiPayload.code.BaseErrorCode;
import com.example.petshield.apiPayload.exception.GeneralException;

public class DogIdHandler extends GeneralException {
    public DogIdHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }

}