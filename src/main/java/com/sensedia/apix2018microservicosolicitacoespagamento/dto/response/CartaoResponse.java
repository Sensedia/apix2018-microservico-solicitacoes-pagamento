package com.sensedia.apix2018microservicosolicitacoespagamento.dto.response;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class CartaoResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(notes = "Número do cartão")
	private final String numero;

	public CartaoResponse(String numero) {
		this.numero = numero;
	}

	public String getNumero() {
		return numero;
	}

}