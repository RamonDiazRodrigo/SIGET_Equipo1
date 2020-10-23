package com.app.SIGET.dominio;

import java.time.LocalTime;

public class Horario {

	private int[][] matrizHorario;
	private static final int DIAS = 7;
	private static final int HUECOS = 48;
	
	//	Así se crea una actividad ahora
	//	actividad r = new actividad("Daily", DayOfWeek.MONDAY, LocalTime.of(9, 00), LocalTime.of(9, 30));

	public Horario() {

		this.matrizHorario = new int[DIAS][HUECOS];

		for (int x = 0; x < this.matrizHorario.length; x++) {
			for (int y = 0; y < this.matrizHorario[x].length; y++) {
				this.matrizHorario[x][y] = 0;
			}
		}
	}
	
	public static Horario String2Horario(String horario) {
		
		
		
	}
	
	public String Horario2String() {
		
		String res = "horario : Array\n";
		
		for (int x = 0; x < DIAS; x++) {
			res+=String.valueOf(x)+" : Array\n";
			for (int y = 0; y < HUECOS; y++) {
				res+=String.valueOf(y)+" : "+this.matrizHorario[x][y]+"\n";
			}
		}
		
		return res;
		
	}

	public void insertarActividad(Actividad actividad) {

		for (int i = Horario.calcularIndice(actividad.getHoraI()); i < Horario.calcularIndice(actividad.getHoraI()); i++) {
			this.matrizHorario[actividad.getDia().getValue()][i] = actividad.getId();
		}
	}

	private static int calcularIndice(LocalTime time) {
		return (time.getHour() - 1) * 2 + (time.getMinute() / 30);
	}

	public boolean estaOcupado(Actividad actividad) {
		for (int i = Horario.calcularIndice(actividad.getHoraI()); i < Horario.calcularIndice(actividad.getHoraI()); i++) {
			if(this.matrizHorario[actividad.getDia().getValue()][i] != 0) {
				return true;
			}
		}
		return false;
	}
}
