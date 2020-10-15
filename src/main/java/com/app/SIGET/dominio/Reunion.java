package com.app.SIGET.dominio;

public class Reunion {
	private String name;
	private String fechaI;
	private String horaI;
	private String horaF;
	
	public Reunion(String nombreReunion, String fechaI, String horaI, String horaF) {
		super();
		this.name = nombreReunion;
		this.fechaI = fechaI;
		this.horaI = horaI;
		this.horaF = horaF;
	}

	public String getNombreReunion() {
		return name;
	}

	public void setNombreReunion(String nombreReunion) {
		this.name = nombreReunion;
	}

	public String getFechaI() {
		return fechaI;
	}

	public void setFechaI(String fechaI) {
		this.fechaI = fechaI;
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
