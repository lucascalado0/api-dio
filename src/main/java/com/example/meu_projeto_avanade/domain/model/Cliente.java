package com.example.meu_projeto_avanade.domain.model;



import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "clientes")//anotação jpa para mapear classe a uma tabela
@Table(name = "tb_clientes") //anotação jpa para definir a minha tabela no banco de dados
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "nome")
public class Cliente {

    @Id //indica o atributo responsavel por ser meu identificador
    @GeneratedValue(strategy = GenerationType.IDENTITY) //indica que o valor id será gerado e incrementado automaticamente pelo bd
    @Column(name = "cliente_id", nullable = false) //indica o nome da propriedade na tabela e que não seja nulo
    private Long id;

    @Column(length = 20, nullable = false, unique = true)
    private String cpf;

    @Column(length = 200, nullable = false)
    private String nome;

    @Column(length = 200, nullable = false)
    private String sobrenome;

    @Column(length = 20, nullable = false, unique = true)
    private String telefone;

    @Column(length = 50, nullable = false, unique = true)
    private String email;

    @Embedded//indica que esse atriuto contem uma classe embutida e que seus membros serão mapeados na entidade principal
    private Endereco endereco;

    @OneToMany(mappedBy = "tutor", cascade = CascadeType.ALL)//indica que um tutor pode ter varios pets associados, na table pets tera uma chave estrangeira apontando para a tabela clientes e todas as operações feitas no cliente, será realizada no pet associado
    private List<Pet> pets;
}
