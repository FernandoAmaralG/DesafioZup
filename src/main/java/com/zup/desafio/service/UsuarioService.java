package com.zup.desafio.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.zup.desafio.entities.Usuario;
import com.zup.desafio.repository.UsuarioRepository;
import com.zup.desafio.repository.exception.EntidadeEmUsoException;
import com.zup.desafio.repository.exception.EntidadeNaoEncontradaException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Transactional
	public Usuario criar(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@Transactional
	public void excluir(String cpf) {
		try {
			Optional<Usuario> usuario = usuarioRepository.findById(cpf);
			if (usuario.isPresent()) {
				usuarioRepository.delete(usuario.get());
			}

		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe um cadastro de usuario com cpf %s.", cpf));
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Usuario de cpf %s não pode ser removido, pois está em uso.", cpf));
		}

	}

	@Transactional(value = TxType.NOT_SUPPORTED)
	public Usuario obter(String cpf) {
		Optional<Usuario> usuario = usuarioRepository.findById(cpf);

		if (usuario.isPresent()) {
			return usuario.get();
		}

		return null;
	}

	@Transactional(value = TxType.NOT_SUPPORTED)
	public List<Usuario> listar() {
		return usuarioRepository.findAll();
	}

	@Transactional
	public Usuario atualizar(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

}
