package com.zup.desafio.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

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

	@Transactional
	public Veiculo criar(String cpf, Veiculo veiculo) {

		Optional<Usuario> usuario = usuarioRepository.findById(cpf);

		if (usuario.isPresent()) {

			veiculo.setUsuario(usuario.get());

			ValorResponse valorResponse = apiFipeService.obterValor(veiculo.getMarca(), veiculo.getModelo(),
					veiculo.getAno());

			if (valorResponse != null) {

				veiculo.setValor(valorResponse.getValor());

			}

			veiculo.calcularDiaRodizio();

			return veiculoRepository.save(veiculo);

		}

		throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de usuario com cpf %s.", cpf));

	}

	@Transactional(value = TxType.NOT_SUPPORTED)
	public List<Veiculo> listar(String cpf) {

		Optional<Usuario> usuario = usuarioRepository.findById(cpf);

		if (usuario.isPresent()) {

			return veiculoRepository.listar(usuario.get());

		}

		return new ArrayList<>(0);

	}

	@Transactional
	public Veiculo atualizar(String cpf, Veiculo veiculo) {

		Optional<Usuario> usuario = usuarioRepository.findById(cpf);

		Veiculo veiculoObtido = veiculoRepository.obter(veiculo.getId(), usuario.get());

		if (veiculoObtido != null) {

			veiculo.setUsuario(usuario.get());

			return veiculoRepository.save(veiculo);

		}

		throw new EntidadeNaoEncontradaException(String.format("Esse veiculo nao pertence ao cpf %s.", cpf));

	}

	@Transactional
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

	@Transactional(value = TxType.NOT_SUPPORTED)
	public Veiculo obter(Long id) {

		Optional<Veiculo> veiculo = veiculoRepository.findById(id);

		if (veiculo.isPresent()) {

			return veiculo.get();

		}

		return null;

	}

}
