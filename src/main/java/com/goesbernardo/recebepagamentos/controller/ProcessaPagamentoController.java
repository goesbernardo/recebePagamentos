package com.goesbernardo.recebepagamentos.controller;

import com.goesbernardo.recebepagamentos.domain.Status;
import com.goesbernardo.recebepagamentos.dto.ProcessaPagamentoDTO;
import com.goesbernardo.recebepagamentos.dto.ProcessaPagamentoResponseDTO;
import com.goesbernardo.recebepagamentos.service.impl.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/pagamentos")
public class ProcessaPagamentoController {


    @Autowired
    private PagamentoService pagamentoService;


    @PostMapping
    public ResponseEntity<ProcessaPagamentoResponseDTO> efetuaPagamento(@RequestBody @Valid ProcessaPagamentoDTO dto) throws Exception {

        pagamentoService.efetuaPagamento(dto);
        ProcessaPagamentoResponseDTO response = new ProcessaPagamentoResponseDTO();
        response.setStatus(Status.AUTORIZADO);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }
}
