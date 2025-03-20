package com.example.meu_projeto_avanade.controller.advices;

import com.example.meu_projeto_avanade.exceptions.cliente_exception.ClienteNotFoundException;
import com.example.meu_projeto_avanade.exceptions.cliente_exception.ClienteillegalArgumentException;
import com.example.meu_projeto_avanade.exceptions.servico_exception.ServicoIllegalArgumentException;
import com.example.meu_projeto_avanade.exceptions.servico_exception.ServicoNotFoundException;
import com.example.meu_projeto_avanade.exceptions.servico_exception.ServicoNullException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ServicoControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ServicoNullException.class)
    public ResponseEntity<Object> capturarErroNull(NullPointerException ex){

        Map<String, Object> body = new HashMap<String, Object>();

        body.put("message", "Verifique os campos de cadastro do serviço!");
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());
        body.put("timestamp", System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(ServicoNotFoundException.class)
    public ResponseEntity<Object> capturarErroNotFound(NoSuchElementException ex){

        Map<String, Object> body = new HashMap<String, Object>();

        body.put("message", "Serviço informado não localizado!");
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("error", HttpStatus.NOT_FOUND.getReasonPhrase());
        body.put("timestamp", System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(ServicoIllegalArgumentException.class)
    public ResponseEntity<Object> capturarErroIllegalArgument(IllegalArgumentException ex){

        Map<String, Object> body = new HashMap<String, Object>();

        body.put("message", "Serviço informado já existe!");
        body.put("status", HttpStatus.CONFLICT.value());
        body.put("error", HttpStatus.CONFLICT.getReasonPhrase());
        body.put("timestamp", System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }



}
