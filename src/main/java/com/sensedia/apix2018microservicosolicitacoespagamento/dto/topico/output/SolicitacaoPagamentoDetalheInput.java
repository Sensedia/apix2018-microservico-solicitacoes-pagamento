package com.sensedia.apix2018microservicosolicitacoespagamento.dto.topico.output;

import java.io.Serializable;

import com.sensedia.apix2018microservicosolicitacoespagamento.domain.enums.StatusEnum;

public class SolicitacaoPagamentoDetalheInput implements Serializable {

	private static final long serialVersionUID = 1L;

	private final StatusEnum status;
	private final String mensagem;

	public SolicitacaoPagamentoDetalheInput(StatusEnum status, String mensagem) {
		this.status = status;
		this.mensagem = mensagem;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public String getMensagem() {
		return mensagem;
	}

}
