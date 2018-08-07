package com.sensedia.apix2018microservicosolicitacoespagamento.pubsub;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gcp.pubsub.core.PubSubOperations;
import org.springframework.cloud.gcp.pubsub.integration.outbound.PubSubMessageHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHandler;
import org.springframework.stereotype.Component;

@Component
public class SolicitacoesPagamentoProducer {

	private static final String CHANNEL = "solicitacoesOutputChannel";

	@Value("${pubsub.topic}")
	private String topicName;

	@Bean
	@ServiceActivator(inputChannel = CHANNEL)
	public MessageHandler messageSender(PubSubOperations pubsubTemplate) {
		return new PubSubMessageHandler(pubsubTemplate, topicName);
	}

	@MessagingGateway(defaultRequestChannel = CHANNEL)
	public interface SolicitacaoPagamentoGateway {
		void sendToPubsub(String input);
	}

}
