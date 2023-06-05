package com.goesbernardo.processapagamentos.controller;

import com.goesbernardo.processapagamentos.dto.ProcessaPagamentoDTO;
import com.goesbernardo.processapagamentos.service.impl.PagamentoService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;

public class ProcessaPagamentoControllerTest {
    @Mock
    PagamentoService pagamentoService;
    @InjectMocks
    RecebePagamentoController processaPagamentoController;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testEfetuaPagamento() throws Exception {
        when(pagamentoService.efetuaPagamento(any())).thenReturn(new ProcessaPagamentoDTO());

        ResponseEntity<ProcessaPagamentoDTO> result = processaPagamentoController.efetuaPagamento(new ProcessaPagamentoDTO());
        Assert.assertNotNull(result);
    }
}

