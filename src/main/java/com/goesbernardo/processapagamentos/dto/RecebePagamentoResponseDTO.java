package com.goesbernardo.processapagamentos.dto;

import com.goesbernardo.processapagamentos.domain.Status;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RecebePagamentoResponseDTO {

    @NotEmpty(message = "o status não pode ser vazio")
    private Status status;
}
