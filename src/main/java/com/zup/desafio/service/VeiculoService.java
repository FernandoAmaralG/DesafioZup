package com.zup.desafio.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.zup.desafio.entities.Usuario;
import com.zup.desafio.entities.Veiculo;
import com.zup.desafio.external.ApiFipeService;
import com.zup.desafio.external.response.ValorResponse;
import com.zup.desafio.repository.UsuarioRepository;
import com.zup.desafio.repository.VeiculoRepository;
import com.zup.desafio.repository.exception.EntidadeEmUsoException;
import com.zup.desafio.repository.exception.EntidadeNaoEncontradaException;

@Service
public class VeiculoService {

	@Autowired
	private VeiculoRepository veiculoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ApiFipeService apiFipeService;

	public String retornaDiaSemana() {

		Calendar calendario = Calendar.getInstance();

		int diaSemana = calendario.get(Calendar.DAY_OF_WEEK);

		return pesquisarDiaSemana(diaSemana);

	}

	public String pesquisarDiaSemana(int _diaSemana) {
		String diaSemana = null;

		switch (_diaSemana) {

		case 1: {
			diaSemana = "Domingo";
			break;
		}
		case 2: {
			diaSemana = "Segunda-Feira";
			break;
		}
		case 3: {
			diaSemana = "Terça-Feira";
			break;
		}
		case 4: {
			diaSemana = "Quarta-Feira";
			break;
		}
		case 5: {
			diaSemana = "Quinta-Feira";
			break;
		}
		case 6: {
			diaSemana = "Sexta-Feira";
			break;
		}
		case 7: {
			diaSemana = "Sábado";
			break;
		}

		}
		return diaSemana;

	}

	public Veiculo criar(String cpf, Veiculo veiculo) {

		Optional<Usuario> usuario = usuarioRepository.findById(cpf);

		if (usuario.isPresent()) {

			veiculo.setUsuario(usuario.get());

			ValorResponse valorResponse = apiFipeService.obterValor(veiculo.getMarca(), veiculo.getModelo(),
					veiculo.getAno());

			if (valorResponse != null) {

				veiculo.setValor(valorResponse.getValor());

				char ultimoCaracterAno = veiculo.getAno().charAt(veiculo.getAno().length() - 3);

				int ultimoNumeroAno = Character.getNumericValue(ultimoCaracterAno);

				if (ultimoNumeroAno == 0 || ultimoNumeroAno == 1) {

					veiculo.setDiaRodizio("Segunda-Feira");

					if (veiculo.getDiaRodizio().equals(retornaDiaSemana())) {

						veiculo.setRodizioAtivo(true);

					} else {

						veiculo.setRodizioAtivo(false);

					}

				} else if (ultimoNumeroAno == 2 || ultimoNumeroAno == 3) {

					veiculo.setDiaRodizio("Terça-Feira");

					if (veiculo.getDiaRodizio().equals(retornaDiaSemana())) {

						veiculo.setRodizioAtivo(true);

					} else {

						veiculo.setRodizioAtivo(false);

					}

				} else if (ultimoNumeroAno == 4 || ultimoNumeroAno == 5) {

					veiculo.setDiaRodizio("Quarta-Feira");

					if (veiculo.getDiaRodizio().equals(retornaDiaSemana())) {

						veiculo.setRodizioAtivo(true);

					} else {

						veiculo.setRodizioAtivo(false);

					}

				} else if (ultimoNumeroAno == 6 || ultimoNumeroAno == 7) {

					veiculo.setDiaRodizio("Quinta-Feira");

					if (veiculo.getDiaRodizio().equals(retornaDiaSemana())) {

						veiculo.setRodizioAtivo(true);

					} else {

						veiculo.setRodizioAtivo(false);

					}

				} else if (ultimoNumeroAno == 8 || ultimoNumeroAno == 9) {

					veiculo.setDiaRodizio("Sexta-Feira");

					if (veiculo.getDiaRodizio().equals(retornaDiaSemana())) {

						veiculo.setRodizioAtivo(true);

					} else {

						veiculo.setRodizioAtivo(false);

					}

				}

			}

			return veiculoRepository.save(veiculo);

		}

		throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de usuario com cpf %s.", cpf));

	}

	public List<Veiculo> listar(String cpf) {

		Optional<Usuario> usuario = usuarioRepository.findById(cpf);

		if (usuario.isPresent()) {

			return veiculoRepository.listar(usuario.get());

		}

		return new ArrayList<>(0);

	}

	public Veiculo atualizar(String cpf, Veiculo veiculo) {

		Optional<Usuario> usuario = usuarioRepository.findById(cpf);

		Veiculo veiculoObtido = veiculoRepository.obter(veiculo.getId(), usuario.get());

		if (veiculoObtido != null) {

			veiculo.setUsuario(usuario.get());

			return veiculoRepository.save(veiculo);

		}

		throw new EntidadeNaoEncontradaException(String.format("Esse veiculo nao pertence ao cpf %s.", cpf));

	}

	public void excluir(Long id) {

		try {

			Optional<Veiculo> veiculo = veiculoRepository.findById(id);

			if (veiculo.isPresent()) {

				veiculoRepository.delete(veiculo.get());

			}

		} catch (EmptyResultDataAccessException e) {

			throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de veículo com id %d.", id));

		} catch (DataIntegrityViolationException e) {

			throw new EntidadeEmUsoException(
					String.format("Veículo de id %d não pode ser removido, pois está em uso.", id));

		}

	}

	public Veiculo obter(Long id) {

		Optional<Veiculo> veiculo = veiculoRepository.findById(id);

		if (veiculo.isPresent()) {

			return veiculo.get();

		}

		return null;

	}

}
