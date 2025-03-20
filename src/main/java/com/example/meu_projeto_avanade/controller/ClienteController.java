package com.example.meu_projeto_avanade.controller;

import com.example.meu_projeto_avanade.domain.model.Cliente;
import com.example.meu_projeto_avanade.exceptions.cliente_exception.ClienteNotFoundException;
import com.example.meu_projeto_avanade.service.impl.ClienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {

    @Autowired
    private ClienteServiceImpl clienteService;

    //esta do jeito que eu quero
    @PostMapping(value = "/cadastrar")
    public ResponseEntity<Cliente> create(@RequestBody Cliente cliente) throws Exception {
        cliente = clienteService.create(cliente);

        return ResponseEntity.ok().body(cliente);
    }

    //esta do jeito que eu quero
    @GetMapping(value = "/buscar/{cpf}")
    public ResponseEntity <Optional<Cliente>> findByCpf(@PathVariable String cpf) throws Exception{
        Optional<Cliente> cliente = clienteService.findByCpf(cpf);

        if (cliente.isPresent()){
            return ResponseEntity.ok(cliente);
        } else {
            throw new ClienteNotFoundException();
        }
    }

    //esta do jeito que eu quero
    @GetMapping(value = "/buscar/todos")
    public ResponseEntity <List<Cliente>> findAll(){
        List<Cliente> clientes = clienteService.findAll();

        return ResponseEntity.ok(clientes);
    }

    //esta do jeito que eu quero
    @PutMapping(value = "/atualizar/{cpf}")
    public ResponseEntity <Cliente> update(@RequestBody Cliente cliente) throws Exception{
        Optional<Cliente> clienteOptional = clienteService.findByCpf(cliente.getCpf());

        if (clienteOptional.isPresent()){
            Cliente clienteUpdate = clienteService.update(cliente.getCpf(), cliente);

            return ResponseEntity.ok(clienteUpdate);
        } else {
            throw new ClienteNotFoundException();
        }
    }

    //esta do jeito que eu quero
    @DeleteMapping(value = "/delete/{cpf}")
    public ResponseEntity<Void> delete(@PathVariable String cpf) throws Exception{
        Optional<Cliente> cliente = clienteService.findByCpf(cpf);
        if (cliente.isPresent()){
            clienteService.delete(cpf);

            return ResponseEntity.noContent().build();
        } else {
            throw new ClienteNotFoundException();
        }
    }
}
