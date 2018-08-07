package com.sensedia.apix2018microservicosolicitacoespagamento.pubsub;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gcp.pubsub.core.PubSubOperations;
import org.springframework.cloud.gcp.pubsub.integration.AckMode;
import org.springframework.cloud.gcp.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import org.springframework.cloud.gcp.pubsub.support.GcpPubSubHeaders;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.sensedia.apix2018microservicosolicitacoespagamento.domain.enums.StatusEnum;
import com.sensedia.apix2018microservicosolicitacoespagamento.dto.topico.output.SolicitacaoPagamentoDetalheInput;
import com.sensedia.apix2018microservicosolicitacoespagamento.dto.topico.output.SolicitacaoPagamentoInput;
import com.sensedia.apix2018microservicosolicitacoespagamento.services.ISolicitacaoPagamentoService;

@Component
public class SolicitacoesPagamentoConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(SolicitacoesPagamentoConsumer.class);

	private static final String CHANNEL = "solicitacoesPagamentoInputChannel";

	@Autowired
	private ObjectMapper objectMapper;

	@Value("${pubsub.subsname}")
	private String subscriptionName;

	@Autowired
	private ISolicitacaoPagamentoService solicitacaoPagamentoService;

	@Bean
	public PubSubInboundChannelAdapter messageChannelAdapter(@Qualifier(CHANNEL) MessageChannel inputChannel,
			PubSubOperations pubSubTemplate) {

		PubSubInboundChannelAdapter adapter = new PubSubInboundChannelAdapter(pubSubTemplate, subscriptionName);
		adapter.setOutputChannel(inputChannel);
		adapter.setAckMode(AckMode.MANUAL);
		return adapter;

	}

	@Bean
	@ServiceActivator(inputChannel = CHANNEL)
	public MessageHandler tratarStatusDeRetorno() {
		return message -> {

			LOGGER.debug("[Solicitacao: {}]. Mensagem recebida: {}", null, message.getPayload());

			AckReplyConsumer consumer = null;

			try {

				consumer = (AckReplyConsumer) message.getHeaders()
													 .get(GcpPubSubHeaders.ACKNOWLEDGEMENT);
				
				String payLoad = message.getPayload()
										.toString();
				
				SolicitacaoPagamentoInput input = objectMapper.readValue(payLoad, SolicitacaoPagamentoInput.class);
				
				StatusEnum status = input.getDetalhes().stream()
								   .map(SolicitacaoPagamentoDetalheInput::getStatus)
								   .filter(StatusEnum.PROCESSADO_FRAUDE::equals)
								   .findFirst()
								   .orElseGet(() -> StatusEnum.PROCESSADO_SUCESSO);
								   
				solicitacaoPagamentoService.atualizarStatus(input.getId(), status);

				consumer.ack();

			} catch (Exception e) {

				LOGGER.error("[Solicitacao: {}] Mensagem: Erro processando mensagem {}", null, message.getPayload());

				if (Objects.nonNull(consumer)) {
					consumer.nack();
				}

			}
		};
	}

}