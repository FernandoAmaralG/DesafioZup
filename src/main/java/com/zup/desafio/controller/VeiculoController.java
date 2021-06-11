package com.zup.desafio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zup.desafio.entities.Veiculo;
import com.zup.desafio.repository.TabelaFipeClient;
import com.zup.desafio.repository.exception.EntidadeEmUsoException;
import com.zup.desafio.repository.exception.EntidadeNaoEncontradaException;
import com.zup.desafio.service.VeiculoService;

@RestController

public class VeiculoController {

	@Autowired
	private VeiculoService veiculoService;

	@Autowired
	public TabelaFipeClient fipeClient;

	@PostMapping("/usuarios/{cpf}/veiculos")
	public ResponseEntity<?> criar(@PathVariable String cpf, @RequestBody Veiculo veiculo) {

		try {

			Veiculo veiculoCriado = veiculoService.criar(cpf, veiculo);

			return ResponseEntity.status(HttpStatus.CREATED).body(veiculoCriado);

		} catch (EntidadeNaoEncontradaException e) {

			return ResponseEntity.badRequest().body(e.getMessage());

		}

	}

	@GetMapping("/usuarios/{cpf}/veiculos")
	public List<Veiculo> listar(@PathVariable String cpf) {

		return veiculoService.listar(cpf);

	}

	@PutMapping("/usuarios/{cpf}/veiculos")
	public ResponseEntity<Veiculo> atualizar(@PathVariable String cpf, @RequestBody Veiculo veiculo) {

		try {

			Veiculo veiculoAtualizado = veiculoService.atualizar(cpf, veiculo);

			return ResponseEntity.ok(veiculoAtualizado);

		} catch (EntidadeNaoEncontradaException e) {

			return ResponseEntity.notFound().build();

		}

	}

	@DeleteMapping("/veiculos/{id}")
	public ResponseEntity<Veiculo> excluir(@PathVariable Long id) {

		try {
			veiculoService.excluir(id);

			return ResponseEntity.noContent().build();

		} catch (EntidadeNaoEncontradaException e) {

			return ResponseEntity.notFound().build();

		} catch (EntidadeEmUsoException e) {

			return ResponseEntity.status(HttpStatus.CONFLICT).build();

		}
	}

}
