package com.example.meu_projeto_avanade.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;


@Embeddable //indica que a classe pode ser embutida em outras entidades
@Data
public class Endereco {

    @Column(length = 20, nullable = false)
    private String cep;

    @Column(length = 50, nullable = false)
    private String rua;

    @Column(name = "numero_casa", nullable = false)
    private Integer numero;

    @Column(length = 50, nullable = false)
    private String bairro;

    @Column(length = 50, nullable = false)
    private String cidade;

    @Column(length = 50, nullable = false)
    private String estado;
}
