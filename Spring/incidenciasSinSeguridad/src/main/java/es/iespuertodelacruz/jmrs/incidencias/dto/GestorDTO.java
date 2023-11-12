package es.iespuertodelacruz.jmrs.incidencias.dto;

import javax.servlet.annotation.ServletSecurity.TransportGuarantee;

import es.iespuertodelacruz.jmrs.incidencias.entity.Gestor;

public class GestorDTO {
	
	private String nombre;
	private String apellidos;
	
	public GestorDTO() {}
	
	public GestorDTO(Gestor g) {
		this.nombre = g.getNombre();
		this.apellidos = g.getApellidos();
	}
	
	public Gestor toGestor() {
		Gestor g = new Gestor();
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

}
