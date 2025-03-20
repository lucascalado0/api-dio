package com.example.meu_projeto_avanade.controller;

import com.example.meu_projeto_avanade.domain.model.Servico;
import com.example.meu_projeto_avanade.exceptions.servico_exception.ServicoNotFoundException;
import com.example.meu_projeto_avanade.service.impl.ServicoServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/servico")
public class ServicoController {
    @Autowired
    private ServicoServiceImpl servicoService;


    @PostMapping(value = "/create")
    public ResponseEntity<Servico> create(@RequestBody Servico servico){
        servico = servicoService.create(servico);

        return ResponseEntity.ok().body(servico);
    }


    @GetMapping(value = "/buscar/{id}")
    public ResponseEntity <Optional<Servico>> findById(@PathVariable Long id){
        Optional<Servico> servico = servicoService.findById(id);

        if (servico.isPresent()){
            return ResponseEntity.ok(servico);
        } else {
            throw new ServicoNotFoundException();
        }

    }

    @GetMapping(value = "/buscar/todos")
    public ResponseEntity<List<Servico>> findByAll(){
        List<Servico> servicos = servicoService.findAll();

        return ResponseEntity.ok(servicos);
    }

    @PutMapping(value = "/atualizar/{id}")
    public ResponseEntity<Servico> update(@RequestBody Servico servico){
        Optional<Servico> servicoOptional = servicoService.findById(servico.getId());

        if (servicoOptional.isPresent()){
            Servico servicoUpdate = servicoService.update(servico.getId(), servico);

            return ResponseEntity.ok(servicoUpdate);
        } else {
            throw new ServicoNotFoundException();
        }
    }

    @DeleteMapping(value = "/deletar/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        Optional<Servico> servico = servicoService.findById(id);

        if (servico.isPresent()){
            servicoService.delete(id);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            throw new ServicoNotFoundException();
        }
    }
}
