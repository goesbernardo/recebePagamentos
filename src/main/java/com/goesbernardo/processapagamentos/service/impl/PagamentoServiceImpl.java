package com.goesbernardo.processapagamentos.service.impl;

import com.goesbernardo.processapagamentos.domain.Pagamento;
import com.goesbernardo.processapagamentos.domain.Status;
import com.goesbernardo.processapagamentos.domain.TipoBandeira;
import com.goesbernardo.processapagamentos.dto.ProcessaPagamentoDTO;
import com.goesbernardo.processapagamentos.repository.PagamentoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagamentoServiceImpl implements PagamentoService {


    public final static String MESSAGE_QUEUE = "pagamento.efetuado";

    public final static String MESSAGE_PROCESSADO = "pagamento.processado";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public ProcessaPagamentoDTO efetuaPagamento(ProcessaPagamentoDTO recebePagamentoDTO) throws AmqpException {

        if (recebePagamentoDTO.getTipoBandeira() == TipoBandeira.DESCONHECIDO){
            throw new RuntimeException("tipo da bandeira do cartão não pode ser desconhecido");
        }

        try {
            rabbitTemplate.convertAndSend(MESSAGE_QUEUE, recebePagamentoDTO);
            rabbitTemplate.receiveAndConvert(MESSAGE_PROCESSADO);
        }catch (Exception ex){
            throw new AmqpException(String.format("erro", ex));
        }

        Pagamento pagamento = modelMapper.map(recebePagamentoDTO, Pagamento.class);
        pagamento.setStatus(Status.AUTORIZADO);
        pagamentoRepository.save(pagamento);

        return modelMapper.map(pagamento, ProcessaPagamentoDTO.class);

    }


}

