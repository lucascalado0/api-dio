package com.example.meu_projeto_avanade.service;

import com.example.meu_projeto_avanade.domain.model.Servico;

import java.util.List;
import java.util.Optional;

public interface ServicoService {
    Servico create(Servico servico) throws Exception;

    Optional<Servico> findById(Long id) throws Exception;

    List<Servico> findAll() throws Exception;

    Servico update(Long id, Servico servico) throws Exception;

    Servico delete(Long id) throws Exception;

}
