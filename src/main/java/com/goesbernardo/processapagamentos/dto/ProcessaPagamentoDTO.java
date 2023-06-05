package com.goesbernardo.processapagamentos.dto;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.goesbernardo.processapagamentos.domain.FormaPagamento;
import com.goesbernardo.processapagamentos.domain.Status;
import com.goesbernardo.processapagamentos.domain.TipoBandeira;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
@JsonSerialize
public class ProcessaPagamentoDTO implements Serializable {



    @NotNull
    @NotEmpty
    private Long id;
    @NotNull(message = "o valor do pagamento é obrigatório")
    private Double valor;
    @NotNull(message = "o status do pagamento é obrigatório")
    @NotEmpty(message = "o status não pode ser vazio")
    @Enumerated(EnumType.STRING)
    @JsonEnumDefaultValue
    private Status status;
    @NotNull
    @NotEmpty(message = "forma de pagamento não pode ser enviado vazio")
    @Enumerated(EnumType.STRING)
    @JsonEnumDefaultValue
    private FormaPagamento formaPagamento;
    @NotNull
    @NotEmpty(message = "tipo da bandeira do cartão não pode ser desconhecido")
    @Enumerated(EnumType.STRING)
    @JsonEnumDefaultValue
    private TipoBandeira tipoBandeira;



}
