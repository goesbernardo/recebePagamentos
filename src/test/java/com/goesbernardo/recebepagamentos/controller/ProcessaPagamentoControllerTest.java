package com.goesbernardo.recebepagamentos.controller;

import com.goesbernardo.recebepagamentos.dto.ProcessaPagamentoDTO;
import com.goesbernardo.recebepagamentos.dto.ProcessaPagamentoResponseDTO;
import com.goesbernardo.recebepagamentos.service.impl.PagamentoService;
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
    ProcessaPagamentoController processaPagamentoController;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testEfetuaPagamento() throws Exception {
        when(pagamentoService.efetuaPagamento(any())).thenReturn(new ProcessaPagamentoDTO());

        ResponseEntity<ProcessaPagamentoResponseDTO> result = processaPagamentoController.efetuaPagamento(new ProcessaPagamentoDTO());
        Assert.assertNotNull(result);
    }
}

