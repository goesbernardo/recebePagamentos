package com.goesbernardo.recebepagamentos.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "TB_PAGAMENTO")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Pagamento {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double valor;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;


}
