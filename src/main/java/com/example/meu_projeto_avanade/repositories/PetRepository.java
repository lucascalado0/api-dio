package com.example.meu_projeto_avanade.repositories;


import com.example.meu_projeto_avanade.domain.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    Optional<Pet> findById(Long id);
    List<Pet> findAll();
    List<Pet> findByNome(String nome);
}
