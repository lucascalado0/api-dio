package com.example.meu_projeto_avanade.repositories;

import com.example.meu_projeto_avanade.domain.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {
    Optional<Servico> findById(Long id);
    List<Servico> findAll();
}
