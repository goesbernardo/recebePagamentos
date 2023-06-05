package com.goesbernardo.recebepagamentos.service.impl;

import com.goesbernardo.recebepagamentos.domain.TipoBandeira;
import com.goesbernardo.recebepagamentos.dto.ProcessaPagamentoDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagamentoServiceImpl implements PagamentoService {


    public final static String MESSAGE_QUEUE = "pagamento.efetuado";

    public final static String MESSAGE_PROCESSADO = "pagamento.processado";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public ProcessaPagamentoDTO efetuaPagamento(ProcessaPagamentoDTO processaPagamentoPagamentoDTO) {

        if (processaPagamentoPagamentoDTO.getTipoBandeira() == TipoBandeira.DESCONHECIDO){
            throw new RuntimeException("tipo da bandeira do cartão não pode ser desconhecido");
        }
        try {
            rabbitTemplate.convertAndSend(MESSAGE_QUEUE, processaPagamentoPagamentoDTO);

            rabbitTemplate.receiveAndConvert(MESSAGE_PROCESSADO, processaPagamentoPagamentoDTO.getId());
        }catch (Exception ex) {
            throw new RuntimeException("erro ao fazer a comunicação com o serviço de processamento");
        }

        return processaPagamentoPagamentoDTO;

    }


}

