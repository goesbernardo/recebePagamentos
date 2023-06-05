package com.goesbernardo.processapagamentos.controller;

import com.goesbernardo.processapagamentos.dto.ProcessaPagamentoDTO;
import com.goesbernardo.processapagamentos.service.impl.PagamentoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/pagamentos")
@Api(value = "recebePagamento")
public class RecebePagamentoController {


    @Autowired
    private PagamentoService pagamentoService;


    @PostMapping
    @ApiOperation(value = "serviço responsável por receber as requisições de pagamentos", code = 201)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProcessaPagamentoDTO> efetuaPagamento(@RequestBody ProcessaPagamentoDTO dto) throws Exception {

        ProcessaPagamentoDTO response = pagamentoService.efetuaPagamento(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }
}
