package com.goesbernardo.recebepagamentos.dto;

import com.goesbernardo.recebepagamentos.domain.Status;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ProcessaPagamentoResponseDTO {

    @NotEmpty(message = "o status não pode ser vazio")
    private Status status;
}
