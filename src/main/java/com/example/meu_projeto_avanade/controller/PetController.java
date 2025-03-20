package com.example.meu_projeto_avanade.controller;



import com.example.meu_projeto_avanade.domain.model.Cliente;
import com.example.meu_projeto_avanade.domain.model.Pet;
import com.example.meu_projeto_avanade.exceptions.pet_exception.PetNotFoundException;
import com.example.meu_projeto_avanade.service.impl.ClienteServiceImpl;
import com.example.meu_projeto_avanade.service.impl.PetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/pet")
public class PetController {

    @Autowired
    private PetServiceImpl petService;
    @Autowired
    private ClienteServiceImpl clienteService;

    //funcionando do jeito certo
    @PostMapping(value = "/cadastrar")
    public ResponseEntity<Pet> create(@RequestBody Pet pet) throws Exception{

        Optional<Cliente> cliente = clienteService.findByCpf(pet.getTutor().getCpf());

        if (cliente.isPresent()){
            Cliente tutor = cliente.get();
            pet.setTutor(tutor);
            tutor.getPets().add(pet);

            Pet novoPet = petService.create(pet);

            return ResponseEntity.ok().body(novoPet);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping(value = "/buscar/{nome}")
    public ResponseEntity <List<Pet>> findByNome(@PathVariable String nome){
        List<Pet> pet = petService.findByName(nome);

        if (pet.isEmpty()){
            throw new PetNotFoundException();
        } else {
            return ResponseEntity.ok(pet);
        }
    }


    @GetMapping(value = "/buscar/todos")
    public ResponseEntity<List<Pet>> findAll(){
        List<Pet> pets = petService.findAll();

        return ResponseEntity.ok(pets);
    }

    @PutMapping(value = "/atualizar/{id}")
    public ResponseEntity <Pet> update(@RequestBody Pet pet) throws Exception{
        Optional<Pet> petOptional = petService.findById(pet.getId());

        if (petOptional.isPresent()){
            Pet petUpdate = petService.update(pet.getId(), pet);

            return ResponseEntity.ok(petUpdate);
        } else {
            throw new PetNotFoundException();
        }
    }

}
