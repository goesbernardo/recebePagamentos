package com.goesbernardo.processapagamentos.service.impl;

import com.goesbernardo.processapagamentos.domain.FormaPagamento;
import com.goesbernardo.processapagamentos.domain.Status;
import com.goesbernardo.processapagamentos.dto.ProcessaPagamentoDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.mockito.Mockito.*;

public class PagamentoServiceImplTest {
    @Mock
    RabbitTemplate rabbitTemplate;
    @InjectMocks
    PagamentoServiceImpl pagamentoServiceImpl;

    @Mock
    ProcessaPagamentoDTO pagamentoDTO;



    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testEfetuaPagamento() throws Exception {
        pagamentoDTO.setId(anyLong());
        pagamentoDTO.setFormaPagamento(FormaPagamento.CARTAO_CREDITO);
        pagamentoDTO.setValor(anyDouble());
        pagamentoDTO.setStatus(Status.ENVIADO);

        ProcessaPagamentoDTO result = pagamentoServiceImpl.efetuaPagamento(pagamentoDTO);
        Assert.assertNotNull(result);
    }
}

