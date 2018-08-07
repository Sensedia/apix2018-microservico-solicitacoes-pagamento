package com.sensedia.apix2018microservicosolicitacoespagamento.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.api.client.http.HttpStatusCodes;
import com.sensedia.apix2018microservicosolicitacoespagamento.domain.SolicitacaoPagamento;
import com.sensedia.apix2018microservicosolicitacoespagamento.dto.request.SolicitacaoPagamentoRequest;
import com.sensedia.apix2018microservicosolicitacoespagamento.dto.response.SolicitacaoPagamentoResponse;
import com.sensedia.apix2018microservicosolicitacoespagamento.services.impl.SolicitacaoPagamentoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "solicitacoes")
@RequestMapping(value = "/solicitacoes")
public class SolicitacaoPagamentoResource {

	@Autowired
	private SolicitacaoPagamentoService solicitacaoPagamentoService;

	@PostMapping
	@ApiOperation(value = "Retorna a solicitação de pagamento com base no ID", response = SolicitacaoPagamentoResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = HttpStatusCodes.STATUS_CODE_CREATED, message = "Registro criado com sucesso"),
			@ApiResponse(code = HttpStatusCodes.STATUS_CODE_SERVER_ERROR, message = "Erro inesperado ocorreu no servidor") })
	public ResponseEntity<SolicitacaoPagamentoResponse> criar(
			@Valid @RequestBody SolicitacaoPagamentoRequest solicitacaoPagamento, UriComponentsBuilder builder) {

		SolicitacaoPagamento domain = solicitacaoPagamento.toDomain();

		Long id = solicitacaoPagamentoService.criar(domain);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("solicitacoes/{id}").buildAndExpand(id).toUri());

		return new ResponseEntity<>(SolicitacaoPagamentoResponse.toResponse(id, domain), headers, HttpStatus.CREATED);

	}

}
