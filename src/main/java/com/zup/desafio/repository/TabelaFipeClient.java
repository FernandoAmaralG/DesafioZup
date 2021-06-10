package com.zup.desafio.repository;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.zup.desafio.external.response.AnoResponse;
import com.zup.desafio.external.response.MarcaResponse;
import com.zup.desafio.external.response.ModeloResponse;
import com.zup.desafio.external.response.ValorResponse;

@FeignClient(name = "fipe", url = "https://parallelum.com.br/fipe/api/v1/carros")
public interface TabelaFipeClient {

	@GetMapping(value = "/marcas")
	public List<MarcaResponse> listarMarcas();

	@GetMapping(value = "/marcas/{codigo}/modelos")
	public ModeloResponse listarModelos(@PathVariable("codigo") String codigo);

	@GetMapping(value = "/marcas/{codigoMarca}/modelos/{codigoModelo}/anos")
	public List<AnoResponse> listarAnos(@PathVariable("codigoMarca") String codigoMarca,
			@PathVariable("codigoModelo") Integer codigoModelo);

	@GetMapping(value = "/marcas/{codigoMarca}/modelos/{codigoModelo}/anos/{ano}")
	public ValorResponse obterValor(@PathVariable("codigoMarca") String codigoMarca,
			@PathVariable("codigoModelo") Integer codigoModelo, @PathVariable("ano") String ano);

}
