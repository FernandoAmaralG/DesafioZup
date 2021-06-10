package com.zup.desafio.external;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zup.desafio.entities.Veiculo;
import com.zup.desafio.external.response.AnoResponse;
import com.zup.desafio.external.response.MarcaResponse;
import com.zup.desafio.external.response.Modelo;
import com.zup.desafio.external.response.ModeloResponse;
import com.zup.desafio.external.response.ValorResponse;
import com.zup.desafio.repository.TabelaFipeClient;

@Service
public class ApiFipeService {

	@Autowired
	public TabelaFipeClient fipeClient;

	public Veiculo veiculo;

	public ValorResponse obterValor(String nomeMarca, String nomeModelo, String anoModelo) {

		List<MarcaResponse> marcas = fipeClient.listarMarcas();

		MarcaResponse marcaObtida = null;

		for (MarcaResponse marcaResponse : marcas) {

			if (marcaResponse.getNome().equals(nomeMarca)) {

				marcaObtida = marcaResponse;

				break;

			}
		}

		Modelo modeloObtido = null;

		if (marcaObtida != null) {

			ModeloResponse modeloResponse = fipeClient.listarModelos(marcaObtida.getCodigo());

			for (Modelo modelo : modeloResponse.getModelos()) {

				if (modelo.getNome().equals(nomeModelo)) {

					modeloObtido = modelo;

					break;

				}

			}

		}

		AnoResponse anoObtido = null;

		if (marcaObtida != null && modeloObtido != null) {

			List<AnoResponse> anosResponse = fipeClient.listarAnos(marcaObtida.getCodigo(), modeloObtido.getCodigo());

			for (AnoResponse ano : anosResponse) {

				if (ano.getCodigo().equals(anoModelo)) {

					anoObtido = ano;

					break;

				}

			}

		}

		if (marcaObtida != null && modeloObtido != null && anoObtido != null) {

			return fipeClient.obterValor(marcaObtida.getCodigo(), modeloObtido.getCodigo(), anoObtido.getCodigo());

		}

		return null;

	}

}
