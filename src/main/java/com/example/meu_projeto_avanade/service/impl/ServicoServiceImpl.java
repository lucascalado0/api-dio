package com.example.meu_projeto_avanade.service.impl;

import com.example.meu_projeto_avanade.domain.model.Servico;
import com.example.meu_projeto_avanade.exceptions.servico_exception.ServicoIllegalArgumentException;
import com.example.meu_projeto_avanade.exceptions.servico_exception.ServicoNotFoundException;
import com.example.meu_projeto_avanade.repositories.ServicoRepository;
import com.example.meu_projeto_avanade.service.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class ServicoServiceImpl implements ServicoService {
    @Autowired
    private ServicoRepository servicoRepository;

    @Override
    public Servico create(Servico servico) {
        if ((servico.getId() != null) && (servicoRepository.existsById(servico.getId()))){
            throw new ServicoIllegalArgumentException();
        }

        return servicoRepository.save(servico);
    }


    @Override
    public Optional<Servico> findById(Long id) {

        Optional<Servico> servico = servicoRepository.findById(id);
        if (servico.isPresent()){
            return servico;
        } else {
            throw new ServicoNotFoundException();
        }
    }


    @Override
    public List<Servico> findAll() {
        return servicoRepository.findAll();
    }


    @Override
    public Servico update(Long id, Servico servico) {
        Optional<Servico> servicoOptional = servicoRepository.findById(id);
        if (servicoOptional.isPresent()){
            Servico servicoUpdate = servicoOptional.get();

            servicoUpdate.setStatusServico(servico.getStatusServico());
            servicoUpdate.setFormaPagamento(servico.getFormaPagamento());
            servicoUpdate.setDataInicio(servico.getDataInicio());
            servicoUpdate.setDataTermino(servico.getDataTermino());
            servico.setStatusPagamento(servico.getStatusPagamento());
            servicoUpdate.setValor(servico.getValor());

            servicoRepository.save(servicoUpdate);

            return servico;
        } else {
            throw new ServicoNotFoundException();
        }
    }


    @Override
    public Servico delete(Long id) {
        Optional<Servico> servicoOptional = servicoRepository.findById(id);
        if (servicoOptional.isPresent()){
            Servico servico = servicoOptional.get();

            servicoRepository.delete(servico);

            return servico;
        } else {
            throw new ServicoNotFoundException();
        }
    }
}
