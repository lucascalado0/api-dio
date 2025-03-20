package com.example.meu_projeto_avanade.domain.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "pets")
@Table(name = "tb_pets")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "nome")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 60, nullable = false)
    private String nome;

    @Column(nullable = false)
    private Integer idade;

    @Column(length = 30, nullable = false)
    private String raca;

    @Column(nullable = false)
    private Double peso;

    @Column(length = 10, nullable = false)
    private String sexo;

    @ManyToOne //muitos pets podem pertencer a um unico tutor
    @JoinColumn(name = "cpf", nullable = false)//define a chave estrangeira cliente_id na tabela pets
    private Cliente tutor;

    @ManyToMany(mappedBy = "pets")
    private List<Servico> servicos;
}
