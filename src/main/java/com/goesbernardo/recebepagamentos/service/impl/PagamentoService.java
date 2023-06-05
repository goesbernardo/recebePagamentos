package com.goesbernardo.recebepagamentos.service.impl;

import com.goesbernardo.recebepagamentos.dto.ProcessaPagamentoDTO;

public interface PagamentoService {

    ProcessaPagamentoDTO efetuaPagamento(ProcessaPagamentoDTO requestPagamentoDTO) throws Exception;
}
