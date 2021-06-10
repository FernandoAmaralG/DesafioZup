package com.zup.desafio.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "usuario")
public class Usuario {

	/*
	 * O primeiro passo deve ser a construção de um cadastro de usuários, sendo
	 * obrigatórios: nome, e-mail, CPF e data de nascimento, sendo que e-mail e CPF
	 * devem ser únicos
	 */

	@EqualsAndHashCode.Include
	@Id
	@Column(name = "cpf", unique = true, nullable = false)
	private String cpf;

	@Column(name = "nome", nullable = false)
	private String nome;

	@Column(name = "email", unique = true, nullable = false)
	private String email;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name = "data_nascimento", nullable = false)
	private LocalDate dataNascimento;

}
