package com.app.SIGET.dominio;

public class Reunion {
	private String name;
	private String fecha;
	private String horaI;
	private String horaF;
	
	public Reunion(String nombreReunion, String fecha, String horaI, String horaF) {
		super();
		this.name = nombreReunion;
		this.fecha = fecha;
		this.horaI = horaI;
		this.horaF = horaF;
	}

	public String getNombreReunion() {
		return name;
	}

	public void setNombreReunion(String nombreReunion) {
		this.name = nombreReunion;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHoraI() {
		return horaI;
	}

	public void setHoraI(String horaI) {
		this.horaI = horaI;
	}

	public String getHoraF() {
		return horaF;
	}

	public void setHoraF(String horaF) {
		this.horaF = horaF;
	}

}
