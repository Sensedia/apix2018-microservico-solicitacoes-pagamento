package com.sensedia.apix2018microservicosolicitacoespagamento.dto.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class UsuarioRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "CPF do usuário obrigatório")
	@ApiModelProperty(notes = "CPF do usuário, apenas numeros", required = true)
	private final String cpf;

	@NotNull(message = "Nome do usuário obrigatório")
	@ApiModelProperty(notes = "Nome do usuário", required = true)
	private final String nome;

	@NotNull(message = "Telefone do usuário obrigatório")
	@ApiModelProperty(notes = "Telefone do usuário", required = true, example = "+55 (99)99999-9999")
	private final String telefone;

	@NotNull(message = "Email do usuário obrigatório")
	@ApiModelProperty(notes = "Email do usuario", required = true)
	private final String email;

	public UsuarioRequest(String cpf, String nome, String telefone, String email) {
		this.cpf = cpf;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public String getNome() {
		return nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getEmail() {
		return email;
	}

}
