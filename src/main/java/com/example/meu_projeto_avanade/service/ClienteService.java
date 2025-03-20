package com.example.meu_projeto_avanade.service;

import com.example.meu_projeto_avanade.domain.model.Cliente;


import java.util.List;
import java.util.Optional;

public interface ClienteService {
    Optional<Cliente> findByCpf(String cpf) throws Exception;

    List<Cliente> findAll();

    Cliente create(Cliente cliente) throws Exception;

    Cliente delete(String cpf) throws Exception;

    Cliente update(String cpf, Cliente cliente) throws Exception;
}
