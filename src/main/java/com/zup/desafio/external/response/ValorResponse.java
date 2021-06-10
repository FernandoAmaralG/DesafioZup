package com.zup.desafio.external.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ValorResponse {

	@JsonProperty("Valor")
	private String valor;

}
