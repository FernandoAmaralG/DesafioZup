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
import com.zup.desafio.util.DataUtils;

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
	private boolean rodizioAtivo;

	@ManyToOne(optional = false)
	@JoinColumn(name = "cpf")
	@JsonIgnore
	private Usuario usuario;

	public void calcularDiaRodizio() {
		char ultimoCaracterAno = getAno().charAt(getAno().length() - 1);

		int ultimoNumeroAno = Character.getNumericValue(ultimoCaracterAno);

		String diaSemana = DataUtils.retornaDiaSemana();

		if (ultimoNumeroAno == 0 || ultimoNumeroAno == 1) {

			setDiaRodizio("Segunda-Feira");

			if (getDiaRodizio().equals(diaSemana)) {

				setRodizioAtivo(true);

			}

		} else if (ultimoNumeroAno == 2 || ultimoNumeroAno == 3) {

			setDiaRodizio("Terça-Feira");

			if (getDiaRodizio().equals(diaSemana)) {

				setRodizioAtivo(true);

			}

		} else if (ultimoNumeroAno == 4 || ultimoNumeroAno == 5) {

			setDiaRodizio("Quarta-Feira");

			if (getDiaRodizio().equals(diaSemana)) {

				setRodizioAtivo(true);

			}

		} else if (ultimoNumeroAno == 6 || ultimoNumeroAno == 7) {

			setDiaRodizio("Quinta-Feira");

			if (getDiaRodizio().equals(diaSemana)) {

				setRodizioAtivo(true);

			}

		} else if (ultimoNumeroAno == 8 || ultimoNumeroAno == 9) {

			setDiaRodizio("Sexta-Feira");

			if (getDiaRodizio().equals(diaSemana)) {

				setRodizioAtivo(true);

			}

		}
	}

}
