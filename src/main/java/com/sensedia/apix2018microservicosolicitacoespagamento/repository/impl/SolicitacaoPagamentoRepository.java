package com.sensedia.apix2018microservicosolicitacoespagamento.repository.impl;

import java.util.Objects;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.DoubleValue;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.LatLngValue;
import com.google.cloud.datastore.StringValue;
import com.google.cloud.datastore.TimestampValue;
import com.google.cloud.datastore.Transaction;
import com.google.cloud.datastore.ValueBuilder;
import com.sensedia.apix2018microservicosolicitacoespagamento.domain.SolicitacaoPagamento;
import com.sensedia.apix2018microservicosolicitacoespagamento.domain.enums.StatusEnum;
import com.sensedia.apix2018microservicosolicitacoespagamento.repository.ISolicitacaoPagamentoRepository;

@Component
public class SolicitacaoPagamentoRepository implements ISolicitacaoPagamentoRepository {

	@Value("${datastore.namespace}")
	private String datastoreNamespace;

	@Value("${datastore.kind}")
	private String datastoreKind;

	private Datastore datastore;

	private KeyFactory keyFactory;

	@PostConstruct
	public void setup() {

		datastore = DatastoreOptions.getDefaultInstance().getService();
		keyFactory = datastore.newKeyFactory().setNamespace(datastoreNamespace).setKind(datastoreKind);

	}

	@Override
	public Long criar(SolicitacaoPagamento solicitacaoPagamento) {
		
		Key key = datastore.allocateId(keyFactory.newKey());
		
		solicitacaoPagamento.criar();

		Entity entity = Entity.newBuilder(key)
				.set(SolicitacaoPagamento.VALOR, build(DoubleValue.newBuilder(solicitacaoPagamento.getValor())))
				.set(SolicitacaoPagamento.STATUS, build(StringValue.newBuilder(solicitacaoPagamento.getStatus().name())))
				.set(SolicitacaoPagamento.DATA_CRIACAO, build(TimestampValue.newBuilder(solicitacaoPagamento.getDataCriacao())))
				.set(SolicitacaoPagamento.GEOLOCALIZACAO, build(LatLngValue.newBuilder(solicitacaoPagamento.getGeolocalizacao())))
				.set(SolicitacaoPagamento.CARTAO_NUMERO, build(StringValue.newBuilder(solicitacaoPagamento.getNumeroCartao())))
				.set(SolicitacaoPagamento.CARTAO_NOME, build(StringValue.newBuilder(solicitacaoPagamento.getNomeCartao())))
				.set(SolicitacaoPagamento.CARTAO_VALIDADE, build(StringValue.newBuilder(solicitacaoPagamento.getValidadeCartao())))
				.set(SolicitacaoPagamento.CARTAO_CVV, build(StringValue.newBuilder(solicitacaoPagamento.getCvvCartao())))
				.set(SolicitacaoPagamento.CODIGO_PAGAMENTO, build(StringValue.newBuilder(solicitacaoPagamento.getCodigoPagamento())))
				.set(SolicitacaoPagamento.USUARIO_CPF, build(StringValue.newBuilder(solicitacaoPagamento.getUsuarioCpf())))
				.set(SolicitacaoPagamento.USUARIO_NOME, build(StringValue.newBuilder(solicitacaoPagamento.getUsuarioNome())))
				.set(SolicitacaoPagamento.USUARIO_TELEFONE, build(StringValue.newBuilder(solicitacaoPagamento.getUsuarioTelefone())))			
				.build();

		datastore.put(entity);

		return entity.getKey().getId();

	}

	@Override
	public void atualizarStatus(final Long id, final StatusEnum status) {

		Transaction transaction = datastore.newTransaction();

		try {

			Entity solicitacao = transaction.get(keyFactory.newKey(id));
			if (Objects.nonNull(solicitacao)) {
				transaction.put(Entity.newBuilder(solicitacao)
						.set(SolicitacaoPagamento.STATUS, status.name()).build());
			}
			transaction.commit();

		} finally {

			if (transaction.isActive()) {
				transaction.rollback();
			}

		}

	}

	@Override
	public SolicitacaoPagamento buscar(final Long id) {

		Entity solicitacao = datastore.get(keyFactory.newKey(id));

		if (Objects.nonNull(solicitacao)) {

			return SolicitacaoPagamento.Builder.newBuilder()
					.id(solicitacao.getKey().getId())
					.valor(solicitacao.getDouble(SolicitacaoPagamento.VALOR))
					.status(StatusEnum.valueOf(solicitacao.getString(SolicitacaoPagamento.STATUS)))
					.dataCriacao(solicitacao.getTimestamp(SolicitacaoPagamento.DATA_CRIACAO))
					.geolocalizacao(solicitacao.getLatLng(SolicitacaoPagamento.GEOLOCALIZACAO))
					.numeroCartao(solicitacao.getString(SolicitacaoPagamento.CARTAO_NUMERO))
					.nomeCartao(solicitacao.getString(SolicitacaoPagamento.CARTAO_NOME))
					.validadeCartao(solicitacao.getString(SolicitacaoPagamento.CARTAO_VALIDADE))
					.cvvCartao(solicitacao.getString(SolicitacaoPagamento.CARTAO_CVV))
					.codigoPagamento(solicitacao.getString(SolicitacaoPagamento.CODIGO_PAGAMENTO))
					.usuarioCPF(solicitacao.getString(SolicitacaoPagamento.USUARIO_CPF))
					.usuarioNome(solicitacao.getString(SolicitacaoPagamento.USUARIO_NOME))
					.usuarioTelefone(solicitacao.getString(SolicitacaoPagamento.USUARIO_TELEFONE))
					.build();

		}

		return null;

	}
	
	private static com.google.cloud.datastore.Value<?> build(ValueBuilder<?, ?, ?> valueBuilder) {
		return valueBuilder.build();
	}

}