package com.example.meu_projeto_avanade.service.impl;

import com.example.meu_projeto_avanade.domain.model.Pet;
import com.example.meu_projeto_avanade.exceptions.pet_exception.PetIllegalArgumentException;
import com.example.meu_projeto_avanade.exceptions.pet_exception.PetNotFoundException;
import com.example.meu_projeto_avanade.exceptions.pet_exception.PetNullException;
import com.example.meu_projeto_avanade.repositories.PetRepository;
import com.example.meu_projeto_avanade.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    private PetRepository petRepository;


    @Override
    public Pet create(Pet pet) {
        if (petRepository.existsById(pet.getId())){
            throw new PetIllegalArgumentException();
        }

        if (pet.getNome() == null || pet.getIdade() == null || pet.getRaca() == null ||
        pet.getTutor() == null || pet.getSexo() == null || pet.getPeso() == null){
            throw new PetNullException();
        }

        return petRepository.save(pet);
    }


    @Override
    public Optional<Pet> findById(Long id) {
        Optional<Pet> pet= petRepository.findById(id);

        if (pet.isPresent()){
            return pet;
        } else {
            throw new PetNotFoundException();
        }
    }


    @Override
    public List<Pet> findByName(String nome) {
        List<Pet> pets = petRepository.findByNome(nome);

        if (pets.isEmpty()){
            throw new PetNotFoundException();
        }

        return pets;
    }


    @Override
    public List<Pet> findAll() {
        return petRepository.findAll();
    }


    @Override
    public Pet update(Long id, Pet pet) {
        Optional<Pet> petOptional = petRepository.findById(id);

        if (petOptional.isPresent()){
            Pet petUpdate = petOptional.get();

            petUpdate.setPeso(pet.getPeso());
            petUpdate.setIdade(pet.getIdade());

            petRepository.save(petUpdate);

            return petUpdate;
        } else {
            throw new PetNotFoundException();
        }
    }
}
