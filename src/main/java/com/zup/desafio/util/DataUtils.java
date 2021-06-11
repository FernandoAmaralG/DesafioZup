package com.zup.desafio.util;

import java.util.Calendar;

public class DataUtils {

	public static String retornaDiaSemana() {

		Calendar calendario = Calendar.getInstance();

		int diaSemana = calendario.get(Calendar.DAY_OF_WEEK);

		return pesquisarDiaSemana(diaSemana);

	}

	public static String pesquisarDiaSemana(int numeroDiaSemana) {

		String diaSemana = null;

		switch (numeroDiaSemana) {

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

}
