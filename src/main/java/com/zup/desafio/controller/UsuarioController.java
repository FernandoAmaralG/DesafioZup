package com.zup.desafio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.zup.desafio.entities.Usuario;
import com.zup.desafio.repository.exception.EntidadeEmUsoException;
import com.zup.desafio.repository.exception.EntidadeNaoEncontradaException;
import com.zup.desafio.service.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	public List<Usuario> listar() {

		return usuarioService.listar();

	}

	@GetMapping("/{cpf}")
	public ResponseEntity<Usuario> obter(@PathVariable String cpf) {

		Usuario usuario = usuarioService.obter(cpf);

		if (usuario != null) {

			return ResponseEntity.status(HttpStatus.CREATED).body(usuario);

		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> criar(@RequestBody Usuario usuario) {

		Usuario usuarioCriado = usuarioService.criar(usuario);

		try {

			return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCriado);

		} catch (EntidadeNaoEncontradaException e) {

			return ResponseEntity.badRequest().body(e.getMessage());

		}

	}

	@PutMapping("/{cpf}")
	public ResponseEntity<Usuario> atualizar(@PathVariable String cpf, @RequestBody Usuario usuario) {

		Usuario usuarioAtual = usuarioService.obter(cpf);

		if (usuarioAtual != null) {

			Usuario usuarioAtualizado = usuarioService.atualizar(usuario);

			return ResponseEntity.ok(usuarioAtualizado);
		}

		return ResponseEntity.notFound().build();

	}

	@DeleteMapping("/{cpf}")
	public ResponseEntity<Usuario> excluir(@PathVariable String cpf) {

		try {

			usuarioService.excluir(cpf);

			return ResponseEntity.noContent().build();

		} catch (EntidadeNaoEncontradaException e) {

			return ResponseEntity.notFound().build();

		} catch (EntidadeEmUsoException e) {

			return ResponseEntity.status(HttpStatus.CONFLICT).build();

		}

	}
}
