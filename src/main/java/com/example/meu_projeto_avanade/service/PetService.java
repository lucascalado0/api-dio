package com.example.meu_projeto_avanade.service;

import com.example.meu_projeto_avanade.domain.model.Pet;

import java.util.List;
import java.util.Optional;

public interface PetService {
    Pet create(Pet pet) throws Exception;

    Optional<Pet> findById(Long id) throws Exception;

    List<Pet> findAll() throws Exception;

    Pet update(Long id, Pet pet) throws Exception;

    List<Pet> findByName(String nome) throws Exception;
}
