package com.sensedia.apix2018microservicosolicitacoespagamento.services;

import com.sensedia.apix2018microservicosolicitacoespagamento.domain.SolicitacaoPagamento;
import com.sensedia.apix2018microservicosolicitacoespagamento.domain.enums.StatusEnum;

public interface ISolicitacaoPagamentoService {

	Long criar(SolicitacaoPagamento solicitacaoPagamento);

	void atualizarStatus(final Long id, final StatusEnum status);

	SolicitacaoPagamento buscar(final Long id);

}