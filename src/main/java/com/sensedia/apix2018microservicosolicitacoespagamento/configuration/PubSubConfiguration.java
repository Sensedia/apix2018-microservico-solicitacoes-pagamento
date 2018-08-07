package com.sensedia.apix2018microservicosolicitacoespagamento.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;

@Configuration
public class PubSubConfiguration {

	@Bean
	public MessageChannel notificacoesChannel() {
		return new DirectChannel();
	}

}