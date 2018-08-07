package com.sensedia.apix2018microservicosolicitacoespagamento.dto.topico.input;

import java.io.Serializable;

import com.google.cloud.Timestamp;
import com.sensedia.apix2018microservicosolicitacoespagamento.domain.SolicitacaoPagamento;
import com.sensedia.apix2018microservicosolicitacoespagamento.domain.enums.StatusEnum;
import com.sensedia.apix2018microservicosolicitacoespagamento.dto.GeolocalizacaoDTO;

public class SolicitacaoPagamentoOutput implements Serializable {

	private static final long serialVersionUID = 1L;

	private final Long id;
	private final Double valor;
	private final StatusEnum status;
	private final Timestamp dataCriacao;
	private final GeolocalizacaoDTO geolocalizacao;
	private final String numeroCartao;
	private final String nomeCartao;
	private final String validadeCartao;
	private final String cvvCartao;
	private final String codigoPagamento;
	private final String usuarioCPF;
	private final String usuarioNome;
	private final String usuarioTelefone;

	public SolicitacaoPagamentoOutput(SolicitacaoPagamento solicitacaoPagamento) {
		this.id = solicitacaoPagamento.getId();
		this.valor = solicitacaoPagamento.getValor();
		this.status = solicitacaoPagamento.getStatus();
		this.dataCriacao = solicitacaoPagamento.getDataCriacao();
		this.geolocalizacao = new GeolocalizacaoDTO(solicitacaoPagamento.getGeolocalizacao().getLatitude(), solicitacaoPagamento.getGeolocalizacao().getLongitude());
		this.numeroCartao = solicitacaoPagamento.getNumeroCartao();
		this.nomeCartao = solicitacaoPagamento.getNomeCartao();
		this.validadeCartao = solicitacaoPagamento.getValidadeCartao();
		this.cvvCartao = solicitacaoPagamento.getCvvCartao();
		this.codigoPagamento = solicitacaoPagamento.getCodigoPagamento();
		this.usuarioCPF = solicitacaoPagamento.getUsuarioCpf();
		this.usuarioNome = solicitacaoPagamento.getUsuarioNome();
		this.usuarioTelefone = solicitacaoPagamento.getUsuarioTelefone();
	}

	public Long getId() {
		return id;
	}

	public Double getValor() {
		return valor;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public Timestamp getDataCriacao() {
		return dataCriacao;
	}

	public GeolocalizacaoDTO getGeolocalizacao() {
		return geolocalizacao;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public String getNomeCartao() {
		return nomeCartao;
	}

	public String getValidadeCartao() {
		return validadeCartao;
	}

	public String getCvvCartao() {
		return cvvCartao;
	}

	public String getCodigoPagamento() {
		return codigoPagamento;
	}

	public String getUsuarioCPF() {
		return usuarioCPF;
	}

	public String getUsuarioNome() {
		return usuarioNome;
	}

	public String getUsuarioTelefone() {
		return usuarioTelefone;
	}

}
