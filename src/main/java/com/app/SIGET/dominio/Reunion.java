package com.app.SIGET.dominio;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicInteger;

public class Reunion {
	private static final AtomicInteger count = new AtomicInteger(1);
	private int id;
	private String name;
	private LocalDate fecha;
	private LocalTime horaI;
	private LocalTime horaF;
	
	public Reunion(String nombreReunion, LocalDate fecha, LocalTime horaI, LocalTime horaF) {
		super();
		this.id = count.incrementAndGet();
		this.name = nombreReunion;
		this.fecha = fecha;
		this.horaI = horaI;
		this.horaF = horaF;
	}

	public String getNombreReunion() {
		return this.name;
	}

	public void setNombreReunion(String nombreReunion) {
		this.name = nombreReunion;
	}
	
	public LocalDate getFecha() {
		return this.fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	public LocalTime getHoraI() {
		return this.horaI;
	}

	public void setHoraI(LocalTime horaI) {
		this.horaI = horaI;
	}
	
	public LocalTime getHoraF() {
		return this.horaF;
	}

	public void setHoraF(LocalTime horaF) {
		this.horaF = horaF;
	}

	public int getId() {
		return this.id;
	}

}
