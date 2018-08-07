package com.sensedia.apix2018microservicosolicitacoespagamento.repository;

import com.sensedia.apix2018microservicosolicitacoespagamento.domain.SolicitacaoPagamento;
import com.sensedia.apix2018microservicosolicitacoespagamento.domain.enums.StatusEnum;

public interface ISolicitacaoPagamentoRepository {

	Long criar(final SolicitacaoPagamento solicitacaoPagamento);

	void atualizarStatus(final Long id, final StatusEnum status);

	SolicitacaoPagamento buscar(final Long id);

}
