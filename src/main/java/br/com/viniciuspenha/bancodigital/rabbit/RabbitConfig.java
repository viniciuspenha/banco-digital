package br.com.viniciuspenha.bancodigital.rabbit;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;

@Configuration
@EnableRabbit
public class RabbitConfig {

    @Value("${validacao.queue}")
    private String validacaoQueue;

    @Bean
    public Queue queue() {
        return new Queue(validacaoQueue, true);
    }
}