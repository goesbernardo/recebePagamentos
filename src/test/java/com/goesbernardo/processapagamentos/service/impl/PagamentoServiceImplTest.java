package com.goesbernardo.processapagamentos.service.impl;

import com.goesbernardo.processapagamentos.domain.FormaPagamento;
import com.goesbernardo.processapagamentos.domain.Status;
import com.goesbernardo.processapagamentos.domain.TipoBandeira;
import com.goesbernardo.processapagamentos.dto.ProcessaPagamentoDTO;
import com.goesbernardo.processapagamentos.repository.PagamentoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PagamentoServiceImplTest {

    @Mock
    private RabbitTemplate mockRabbitTemplate;
    @Mock
    private PagamentoRepository mockPagamentoRepository;
    @Mock
    private ModelMapper mockModelMapper;

    @InjectMocks
    private PagamentoServiceImpl pagamentoServiceImplUnderTest;


    @Test
    void testEfetuaPagamento_RabbitTemplateConvertAndSendThrowsAmqpException() {
        // Setup
        final ProcessaPagamentoDTO recebePagamentoDTO = new ProcessaPagamentoDTO();
        recebePagamentoDTO.setId(0L);
        recebePagamentoDTO.setValor(0.0);
        recebePagamentoDTO.setStatus(Status.ENVIADO);
        recebePagamentoDTO.setFormaPagamento(FormaPagamento.CARTAO_CREDITO);
        recebePagamentoDTO.setTipoBandeira(TipoBandeira.VISA);

        // Configure RabbitTemplate.convertAndSend(...).
        final ProcessaPagamentoDTO object = new ProcessaPagamentoDTO();
        object.setId(0L);
        object.setValor(0.0);
        object.setStatus(Status.ENVIADO);
        object.setFormaPagamento(FormaPagamento.CARTAO_CREDITO);
        object.setTipoBandeira(TipoBandeira.VISA);
        doThrow(AmqpException.class).when(mockRabbitTemplate).convertAndSend("pagamento.efetuado", object);

        // Run the test
        assertThatThrownBy(() -> pagamentoServiceImplUnderTest.efetuaPagamento(recebePagamentoDTO))
                .isInstanceOf(AmqpException.class);
    }

    @Test
    void testEfetuaPagamento_RabbitTemplateReceiveAndConvertThrowsAmqpException() {
        // Setup
        final ProcessaPagamentoDTO recebePagamentoDTO = new ProcessaPagamentoDTO();
        recebePagamentoDTO.setId(0L);
        recebePagamentoDTO.setValor(0.0);
        recebePagamentoDTO.setStatus(Status.ENVIADO);
        recebePagamentoDTO.setFormaPagamento(FormaPagamento.CARTAO_CREDITO);
        recebePagamentoDTO.setTipoBandeira(TipoBandeira.VISA);

        when(mockRabbitTemplate.receiveAndConvert("pagamento.processado")).thenThrow(AmqpException.class);

        // Run the test
        assertThatThrownBy(() -> pagamentoServiceImplUnderTest.efetuaPagamento(recebePagamentoDTO))
                .isInstanceOf(AmqpException.class);

        // Confirm RabbitTemplate.convertAndSend(...).
        final ProcessaPagamentoDTO object = new ProcessaPagamentoDTO();
        object.setId(0L);
        object.setValor(0.0);
        object.setStatus(Status.ENVIADO);
        object.setFormaPagamento(FormaPagamento.CARTAO_CREDITO);
        object.setTipoBandeira(TipoBandeira.VISA);
        verify(mockRabbitTemplate).convertAndSend("pagamento.efetuado", object);
    }
}
