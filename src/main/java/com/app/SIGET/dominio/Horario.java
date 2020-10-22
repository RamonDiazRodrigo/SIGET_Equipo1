package com.app.SIGET.dominio;

import java.time.LocalDate;
import java.time.LocalTime;

public class Horario {

	private int[][] matrizHorario;
	
	//	Así se crea una reunion ahora
	//	Reunion r = new Reunion("Daily", LocalDate.of(2020, Month.OCTOBER, 10), LocalTime.of(9, 00), LocalTime.of(9, 30));

	public Horario() {

		this.matrizHorario = new int[7][48];

		for (int x = 0; x < this.matrizHorario.length; x++) {
			for (int y = 0; y < this.matrizHorario[x].length; y++) {
				this.matrizHorario[x][y] = 0;
			}
		}
	}

	public void aniadirReunion(Reunion reunion) {
		LocalDate fecha = reunion.getFecha();

		for (int i = Horario.calcularIndice(reunion.getHoraI()); i < Horario.calcularIndice(reunion.getHoraI()); i++) {
			this.matrizHorario[fecha.getDayOfWeek().getValue()][i] = reunion.getId();
		}
	}

	private static int calcularIndice(LocalTime time) {
		return (time.getHour() - 1) * 2 + (time.getMinute() / 30);
	}

	public boolean estaOcupado(int[][] matrizHorario) {
		boolean ocupado = false;
		for (int x = 0; x < matrizHorario.length; x++) {
			for (int y = 0; y < matrizHorario[x].length; y++) {
				if (matrizHorario[x][y] != 0) {
					ocupado = true;
				}
			}
		}
		return ocupado;
	}
}
