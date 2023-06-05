package com.goesbernardo.recebepagamentos.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data

@Entity
@AllArgsConstructor
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCartao;
    private String nome;
    private String numero;

    private LocalDate dataValidade;
    @Enumerated(EnumType.STRING)
    private TipoBandeira tipoBandeira;

    public Cartao(String nome, String numero, LocalDate dataValidade) {
        super();
        this.nome = nome;
        this.numero = numero;
        this.dataValidade = dataValidade;
    }
    public Cartao() {

    }
}
