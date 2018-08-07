package com.sensedia.apix2018microservicosolicitacoespagamento.dto.topico.output;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

public class SolicitacaoPagamentoInput implements Serializable {

	private static final long serialVersionUID = 1L;

	private final Long id;
	private final Collection<SolicitacaoPagamentoDetalheInput> detalhes;

	public SolicitacaoPagamentoInput(Long id, Collection<SolicitacaoPagamentoDetalheInput> detalhes) {
		this.id = id;
		this.detalhes = detalhes;
	}

	public Long getId() {
		return id;
	}

	public Collection<SolicitacaoPagamentoDetalheInput> getDetalhes() {
		return Collections.unmodifiableCollection(detalhes);
	}

}
