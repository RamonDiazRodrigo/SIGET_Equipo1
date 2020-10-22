package com.app.SIGET.dominio;

public class Horario {
	
	String [][] matriz_horario = new String[14][7];
	Reunion r = new Reunion("Daily","VIERNES", "14", "10");
	
	public static void rellenarHorario (String [][] matriz_horario) {
		int horas = 9;
		for (int x=0; x < matriz_horario.length; x++) {
			for (int y=0; y < matriz_horario[x].length; y++) { 
				  matriz_horario[x][y] = "-";
			}
		}
		matriz_horario[0][0] = "HORAS";
		matriz_horario[0][1] = "LUNES";
		matriz_horario[0][2] = "MARTES";
		matriz_horario[0][3] = "MIERCOLES";
		matriz_horario[0][4] = "JUEVES";
		matriz_horario[0][5] = "VIERNES";
		matriz_horario[0][6] = "SABADO";
		// Relleno los bordes izquierdos con las horas
		for (int x=1; x < matriz_horario.length; x++) {
			matriz_horario[x][0] = (Integer.toString(horas) + ":00");
			horas ++;
		}
	}
	
	public static void mostrarHorario (String [][] matriz_horario) {
		for (int x=0; x < matriz_horario.length; x++) {
			for (int y=0; y < matriz_horario[x].length; y++) { 
				System.out.print(matriz_horario[x][y] +"|");
			}
			System.out.println();
		}
	}
	
	
	public static void aniadirReunion (Reunion reunion, String [][] matriz_horario) {	
		int f = Integer.parseInt(reunion.getHoraI());
		int c = 0;
		for (int y=0; y < matriz_horario[0][y].length(); y++) { 
			if (matriz_horario[0][y] == reunion.getFecha()) {
				c = y;
			}
		}
		matriz_horario[f-8][c] = reunion.getNombreReunion();
		mostrarHorario(matriz_horario);
	}
	
	public boolean estaOcupado (String [][] matriz_horario) { // no es 100% funcional
		boolean ocupado = false;
		for (int x=0; x < matriz_horario.length; x++) {
			for (int y=0; y < matriz_horario[x].length; y++) { 
				if (matriz_horario[x][y] != "-") {
					ocupado = true;
				}
			}
		}
		return ocupado;
	}
}
