package com.sensedia.apix2018microservicosolicitacoespagamento.dto.response;

import java.io.Serializable;
import java.util.Date;

import com.sensedia.apix2018microservicosolicitacoespagamento.domain.SolicitacaoPagamento;
import com.sensedia.apix2018microservicosolicitacoespagamento.domain.enums.StatusEnum;
import com.sensedia.apix2018microservicosolicitacoespagamento.dto.GeolocalizacaoDTO;

import io.swagger.annotations.ApiModelProperty;

public class SolicitacaoPagamentoResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(notes = "Valor da solicitação de pagamento")
	private final Double valor;
	
	@ApiModelProperty(notes = "Data da solicitação")
	private final Date dataCriacao;

	@ApiModelProperty(notes = "Status da solicitação")
	private final StatusEnum status;
	
	@ApiModelProperty(notes = "Código de pagamento")
	private final String codigoPagamento;
	
	@ApiModelProperty(notes = "Cartão da solicitação")
	private final CartaoResponse cartao;
	
	@ApiModelProperty(notes = "Usuário da solicitação")
	private final UsuarioResponse usuario;
	
	@ApiModelProperty(notes = "Geolocalização")
	private final GeolocalizacaoDTO geolocalizacao;

	private SolicitacaoPagamentoResponse(Builder builder) {
		this.valor = builder.valor;
		this.dataCriacao = builder.dataCriacao;
		this.status = builder.status;
		this.codigoPagamento = builder.codigoPagamento;
		this.cartao = builder.cartao;
		this.usuario = builder.usuario;
		this.geolocalizacao = builder.geolocalizacao;
	}

	public static class Builder {

		private Double valor;
		private Date dataCriacao;
		private StatusEnum status;
		private String codigoPagamento;
		private CartaoResponse cartao;
		private UsuarioResponse usuario;
		private GeolocalizacaoDTO geolocalizacao;

		public Builder valor(Double valor) {
			this.valor = valor;
			return this;
		}

		public Builder dataCriacao(Date dataCriacao) {
			this.dataCriacao = dataCriacao;
			return this;
		}

		public Builder status(StatusEnum status) {
			this.status = status;
			return this;
		}

		public Builder codigoPagamento(String codigoPagamento) {
			this.codigoPagamento = codigoPagamento;
			return this;
		}

		public Builder cartao(CartaoResponse cartao) {
			this.cartao = cartao;
			return this;
		}

		public Builder usuario(UsuarioResponse usuario) {
			this.usuario = usuario;
			return this;
		}

		public Builder geolocalizacao(GeolocalizacaoDTO geolocalizacao) {
			this.geolocalizacao = geolocalizacao;
			return this;
		}

		public SolicitacaoPagamentoResponse build() {
			return new SolicitacaoPagamentoResponse(this);
		}

	}

	public Double getValor() {
		return valor;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public String getCodigoPagamento() {
		return codigoPagamento;
	}

	public CartaoResponse getCartao() {
		return cartao;
	}

	public UsuarioResponse getUsuario() {
		return usuario;
	}

	public GeolocalizacaoDTO getGeolocalizacao() {
		return geolocalizacao;
	}

	public static SolicitacaoPagamentoResponse toResponse(final Long id, final SolicitacaoPagamento domain) {
		return new SolicitacaoPagamentoResponse.Builder()
				.valor(domain.getValor())
				.dataCriacao(domain.getDataCriacao().toDate())
				.status(domain.getStatus())
				.codigoPagamento(domain.getCodigoPagamento())
				.cartao(new CartaoResponse(domain.getNumeroCartao()))
				.usuario(new UsuarioResponse(domain.getUsuarioNome()))
				.geolocalizacao(new GeolocalizacaoDTO(domain.getGeolocalizacao().getLatitude(), domain.getGeolocalizacao().getLongitude()))
				.build();
	}

}
