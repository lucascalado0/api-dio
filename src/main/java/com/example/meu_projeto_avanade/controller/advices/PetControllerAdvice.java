package com.example.meu_projeto_avanade.controller.advices;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@ControllerAdvice
public class PetControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Object> capturarErroNull(NullPointerException ex){
        Map<String, Object> body = new HashMap<String, Object>();

        body.put("Message", "Verifique os campos de cadastro do pet!");
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());
        body.put("timestamp", System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler
    public ResponseEntity<Object> capturarErroNotFound(NoSuchElementException ex){
        Map<String, Object> body = new HashMap<String, Object>();

        body.put("Message", "Pet não localizado!");
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());
        body.put("timestamp", System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler
    public ResponseEntity<Object> capturarErroIllegal(IllegalArgumentException ex){
        Map<String, Object> body = new HashMap<String, Object>();

        body.put("Message", "Verifique os campos de cadastro");
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());
        body.put("timestamp", System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
}
