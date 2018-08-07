package com.sensedia.apix2018microservicosolicitacoespagamento.dto.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class CartaoRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "Nome do dono do cartão obrigatório")
	@ApiModelProperty(notes = "Nome do dono do cartão", required = true)
	private final String nome;

	@NotNull(message = "Número do cartão obrigatório")
	@ApiModelProperty(notes = "Número do cartão", required = true)
	private final String numero;

	@NotNull(message = "Validade do cartão obrigatório")
	@ApiModelProperty(notes = "Validade do cartão", required = true, example = "08;2018")
	private final String validade;

	@NotNull(message = "Código de segurança do cartão obrigatório")
	@ApiModelProperty(notes = "Código de segurança do cartão", required = true, example = "123")
	private final String cvv;

	public CartaoRequest(String nome, String numero, String validade, String cvv) {
		this.nome = nome;
		this.numero = numero;
		this.validade = validade;
		this.cvv = cvv;
	}

	public String getNome() {
		return nome;
	}

	public String getNumero() {
		return numero;
	}

	public String getValidade() {
		return validade;
	}

	public String getCvv() {
		return cvv;
	}

}
