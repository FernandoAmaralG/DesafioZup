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

		MarcaResponse marcaObtida = obterMarca(nomeMarca);

		Modelo modeloObtido = null;

		if (marcaObtida != null) {

			modeloObtido = obterModelo(nomeModelo, marcaObtida);

		}

		AnoResponse anoObtido = null;

		if (marcaObtida != null && modeloObtido != null) {

			anoObtido = obterAno(anoModelo, marcaObtida, modeloObtido);

		}

		if (marcaObtida != null && modeloObtido != null && anoObtido != null) {

			return fipeClient.obterValor(marcaObtida.getCodigo(), modeloObtido.getCodigo(), anoObtido.getCodigo());

		}

		return null;

	}

	private AnoResponse obterAno(String anoModelo, MarcaResponse marcaObtida, Modelo modeloObtido) {

		List<AnoResponse> anosResponse = fipeClient.listarAnos(marcaObtida.getCodigo(), modeloObtido.getCodigo());

		for (AnoResponse ano : anosResponse) {

			if (ano.getCodigo().startsWith(anoModelo)) {

				return ano;

			}

		}
		return null;
	}

	private Modelo obterModelo(String nomeModelo, MarcaResponse marcaObtida) {

		ModeloResponse modeloResponse = fipeClient.listarModelos(marcaObtida.getCodigo());

		for (Modelo modelo : modeloResponse.getModelos()) {

			if (modelo.getNome().equals(nomeModelo)) {

				return modelo;

			}

		}

		return null;
	}

	private MarcaResponse obterMarca(String nomeMarca) {

		List<MarcaResponse> marcas = fipeClient.listarMarcas();

		for (MarcaResponse marcaResponse : marcas) {

			if (marcaResponse.getNome().equals(nomeMarca)) {

				return marcaResponse;

			}
		}

		return null;
	}

}
