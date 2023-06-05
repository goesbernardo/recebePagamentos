package com.goesbernardo.processapagamentos.service.impl;

import com.goesbernardo.processapagamentos.dto.ProcessaPagamentoDTO;

public interface PagamentoService {

    ProcessaPagamentoDTO efetuaPagamento(ProcessaPagamentoDTO requestPagamentoDTO) throws Exception;
}
