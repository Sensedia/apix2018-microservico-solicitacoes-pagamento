package com.sensedia.apix2018microservicosolicitacoespagamento.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sensedia.apix2018microservicosolicitacoespagamento.domain.SolicitacaoPagamento;
import com.sensedia.apix2018microservicosolicitacoespagamento.domain.enums.StatusEnum;
import com.sensedia.apix2018microservicosolicitacoespagamento.pubsub.SolicitacoesPagamentoProducer;
import com.sensedia.apix2018microservicosolicitacoespagamento.repository.ISolicitacaoPagamentoRepository;
import com.sensedia.apix2018microservicosolicitacoespagamento.services.ISolicitacaoPagamentoService;

@Service
public class SolicitacaoPagamentoService implements ISolicitacaoPagamentoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SolicitacaoPagamentoService.class);

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ISolicitacaoPagamentoRepository repository;

	@Autowired
	private SolicitacoesPagamentoProducer.SolicitacaoPagamentoGateway solicitacaoPagamentoGateway;

	@Override
	public Long criar(SolicitacaoPagamento solicitacaoPagamento) {

		LOGGER.info("Solicitacao de criacao: {}", solicitacaoPagamento);

		Long id = repository.criar(solicitacaoPagamento);

		try {

			SolicitacaoPagamento build = solicitacaoPagamento.getBuilder().id(id).build();
			solicitacaoPagamentoGateway.sendToPubsub(objectMapper.writeValueAsString(build));

		} catch (Exception ex) {

			LOGGER.error("[Solicitacao: {}] ID gerado com sucesso! Solicitacao: {}", id, solicitacaoPagamento);

		}

		LOGGER.info("ID '{}' gerado com sucesso! Solicitacao: {}", id, solicitacaoPagamento);

		return id;

	}

	@Override
	public void atualizarStatus(final Long id, final StatusEnum status) {

		LOGGER.info("Atualizando o status da solicitacao {} para {}", id, status);

		repository.atualizarStatus(id, status);

		LOGGER.info("Atualizado com sucesso o status da solicitacao {} para {}", id, status);

	}

}
