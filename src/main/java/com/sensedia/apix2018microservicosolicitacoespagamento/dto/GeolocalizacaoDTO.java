package com.sensedia.apix2018microservicosolicitacoespagamento.dto;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class GeolocalizacaoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "Latitude obrigatória")
	@Min(value = -90, message = "No minimo -90")
	@Max(value = 90, message = "No maximo 90")
	@ApiModelProperty(notes = "Latitude entre −90 e 90", required = true)
	private final Double latitude;

	@NotNull(message = "Longitude obrigatória")
	@Min(value = -180, message = "No minimo -180")
	@Max(value = 180, message = "No maximo 180")
	@ApiModelProperty(notes = "Longitude entre −180 to 180", required = true)
	private final Double longitude;

	public GeolocalizacaoDTO(Double latitude, Double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

}