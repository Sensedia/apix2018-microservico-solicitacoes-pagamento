package com.sensedia.apix2018microservicosolicitacoespagamento.dto.request;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.google.cloud.datastore.LatLng;
import com.sensedia.apix2018microservicosolicitacoespagamento.domain.SolicitacaoPagamento;
import com.sensedia.apix2018microservicosolicitacoespagamento.dto.GeolocalizacaoDTO;

import io.swagger.annotations.ApiModelProperty;

public class SolicitacaoPagamentoRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "Valor obrigatório")
	@Min(value = 0, message = "Valor deve ser positivo")
	@ApiModelProperty(notes = "Valor", required = true)
	private final Double valor;

	@NotNull(message = "Código de pagamento obrigatório")
	@ApiModelProperty(notes = "Codigo de pagamento", required = true)
	private final String codigoPagamento;

	@NotNull(message = "Detalhes do cartão são obrigatórios")
	@ApiModelProperty(notes = "Detalhes do cartão", required = true)
	private final CartaoRequest cartao;

	@NotNull(message = "Detalhes do usuário/solicitante obrigatório")
	@ApiModelProperty(notes = "Detalhes do usuário/solicitante", required = true)
	private final UsuarioRequest usuario;

	@NotNull(message = "Detalhes da geolocalização obrigatório")
	@ApiModelProperty(notes = "Detalhes da geolocalização", required = true)
	private final GeolocalizacaoDTO geolocalizacao;

	public SolicitacaoPagamentoRequest(Double valor, String codigoPagamento, CartaoRequest cartao,
			UsuarioRequest usuario, GeolocalizacaoDTO geolocalizacao) {
		this.valor = valor;
		this.codigoPagamento = codigoPagamento;
		this.cartao = cartao;
		this.usuario = usuario;
		this.geolocalizacao = geolocalizacao;
	}

	public Double getValor() {
		return valor;
	}

	public String getCodigoPagamento() {
		return codigoPagamento;
	}

	public CartaoRequest getCartao() {
		return cartao;
	}

	public UsuarioRequest getUsuario() {
		return usuario;
	}

	public GeolocalizacaoDTO getGeolocalizacao() {
		return geolocalizacao;
	}

	public SolicitacaoPagamento toDomain() {
		return new SolicitacaoPagamento.Builder()
				.valor(this.valor).codigoPagamento(this.codigoPagamento)
				.nomeCartao(this.getCartao().getNome()).numeroCartao(this.getCartao().getNumero())
				.validadeCartao(this.getCartao().getValidade()).cvvCartao(this.getCartao().getCvv())
				.usuarioCPF(this.getUsuario().getCpf()).usuarioNome(this.getUsuario().getNome())
				.usuarioTelefone(this.getUsuario().getTelefone())
				.geolocalizacao(LatLng.of(this.geolocalizacao.getLatitude(), this.geolocalizacao.getLongitude()))
				.build();
	}

}
