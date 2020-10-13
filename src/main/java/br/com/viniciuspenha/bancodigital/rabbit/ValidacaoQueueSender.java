package br.com.viniciuspenha.bancodigital.rabbit;

import br.com.viniciuspenha.bancodigital.model.dto.ClienteDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoQueueSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidacaoQueueSender.class);

    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;
    private final ObjectMapper mapper;

    public ValidacaoQueueSender(RabbitTemplate rabbitTemplate, Queue queue, ObjectMapper mapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.queue = queue;
        this.mapper = mapper;
    }

    public void send(ClienteDTO cliente) {
        try {
            String mensagem = mapper.writeValueAsString(cliente);
            LOGGER.info("ValidacaoQueueSender.send - Enviando cliente para fila de validacao");
            rabbitTemplate.convertAndSend(this.queue.getName(), mensagem);
        } catch (Exception e) {
            LOGGER.error("ValidacaoQueueSender.send - Falha ao enviar mensagem: {}", e.getMessage());
        }
    }
}