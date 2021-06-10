package com.zup.desafio.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "veiculo")
public class Veiculo {

	/*
	 * O segundo passo é criar um cadastro de veículos, sendo obrigatórios: Marca,
	 * Modelo do Veículo e Ano. E o serviço deve consumir a API da FIPE
	 * (https://deividfortuna.github.io/fipe/) para obter os dados do valor do
	 * veículo baseado nas informações inseridas.
	 */

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "marca", nullable = false)
	private String marca;

	@Column(name = "modelo", nullable = false)
	private String modelo;

	@Column(name = "ano", nullable = false, length = 10)
	private String ano;

	@Column(name = "valor", nullable = true)
	private String valor;

	@Column(name = "diaRodizio")
	private String diaRodizio;

	@Column(name = "rodizioAtivo")
	private Boolean rodizioAtivo;

	@ManyToOne(optional = false)
	@JoinColumn(name = "cpf")
	@JsonIgnore
	private Usuario usuario;

}
