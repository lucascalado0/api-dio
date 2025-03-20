package com.example.meu_projeto_avanade.domain.model;


import com.example.meu_projeto_avanade.domain.enums.StatusServico;
import com.example.meu_projeto_avanade.domain.enums.TipoServico;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDate;
import java.util.List;

@Data
@Entity(name = "servico")
@Table(name = "tb_servicos")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_servico",length = 30, nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoServico tipoServico;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "data_inicio", columnDefinition = "DATE", nullable = false)
    private LocalDate dataInicio;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "data_termino", columnDefinition = "DATE", nullable = false)
    private LocalDate dataTermino;

    @Column(name = "status_pagamento", nullable = false)
    private Boolean statusPagamento;

    @Column(name = "forma_pagamento", length = 30, nullable = false)
    private String formaPagamento;

    @Column(nullable = false)
    private Double valor;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_servico", length = 15, nullable = false)
    private StatusServico statusServico;

    @ManyToMany
    @JoinTable(
            name = "pet_servico",
            joinColumns = @JoinColumn(name = "servico_id"),
            inverseJoinColumns = @JoinColumn(name = "pet_id"))
    private List<Pet> pets;
}
