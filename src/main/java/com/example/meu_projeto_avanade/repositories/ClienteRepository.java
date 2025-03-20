package com.example.meu_projeto_avanade.repositories;
import com.example.meu_projeto_avanade.domain.model.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByCpf(String cpf);
    List<Cliente> findAll();
}
