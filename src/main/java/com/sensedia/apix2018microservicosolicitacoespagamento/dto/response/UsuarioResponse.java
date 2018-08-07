package com.sensedia.apix2018microservicosolicitacoespagamento.dto.response;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class UsuarioResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(notes = "Nome do usuário")
	private final String nome;

	public UsuarioResponse(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

}