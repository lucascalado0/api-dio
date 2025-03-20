package com.example.meu_projeto_avanade.service.impl;

import com.example.meu_projeto_avanade.domain.model.Cliente;
import com.example.meu_projeto_avanade.exceptions.cliente_exception.ClienteNotFoundException;
import com.example.meu_projeto_avanade.exceptions.cliente_exception.ClienteNullException;
import com.example.meu_projeto_avanade.exceptions.cliente_exception.ClienteillegalArgumentException;
import com.example.meu_projeto_avanade.repositories.ClienteRepository;
import com.example.meu_projeto_avanade.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;


    @Override
    public Cliente create(Cliente cliente) throws Exception{

        if (cliente.getCpf() == null || cliente.getNome() == null || cliente.getSobrenome() == null ||
        cliente.getEmail() == null || cliente.getTelefone() == null || cliente.getEndereco() == null){
            throw new ClienteNullException();
        }
        if (clienteRepository.findByCpf(cliente.getCpf()).isPresent()){
            throw new ClienteillegalArgumentException();
        }

        return clienteRepository.save(cliente);
    }


    @Override
    public Optional<Cliente> findByCpf(String cpf){
        Optional<Cliente> clienteOptional = clienteRepository.findByCpf(cpf);
        if (clienteOptional.isPresent()){
            return clienteOptional;
        } else {
            throw new ClienteNotFoundException();
        }
    }


    @Override
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }


    @Override
    public Cliente delete(String cpf) {
        Optional<Cliente> clienteOptional = clienteRepository.findByCpf(cpf);
        if (clienteOptional.isPresent()){
            Cliente cliente = clienteOptional.get();

            clienteRepository.delete(cliente);

            return cliente;
        } else {
            throw new ClienteNotFoundException();
        }
    }


    @Override
    public Cliente update(String cpf, Cliente cliente) {
        Optional<Cliente> clienteOptional = clienteRepository.findByCpf(cpf);

        if (clienteOptional.isPresent()){
            Cliente clienteUpdate = clienteOptional.get();

            clienteUpdate.setEndereco(cliente.getEndereco());
            clienteUpdate.setTelefone(cliente.getTelefone());
            clienteUpdate.setEmail(cliente.getEmail());

            return clienteRepository.save(clienteUpdate);
        } else {
            throw new ClienteNotFoundException();
        }
    }
}
