package es.iespuertodelacruz.jmrs.incidencias.dto;

import javax.servlet.annotation.ServletSecurity.TransportGuarantee;

import es.iespuertodelacruz.jmrs.incidencias.entity.Gestore;

public class GestorDTO {
	
	private String dni;
	private String nombre;
	private String apellidos;
	
	public GestorDTO() {}
	
	public GestorDTO(Gestore g) {
		this.dni = g.getDni();
		this.nombre = g.getNombre();
		this.apellidos = g.getApellidos();
	}
	
	public Gestore toGestor() {
		Gestore g = new Gestore();
		g.setDni(dni);
		g.setNombre(nombre);
		g.setApellidos(apellidos);
		
		return g;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}
}
