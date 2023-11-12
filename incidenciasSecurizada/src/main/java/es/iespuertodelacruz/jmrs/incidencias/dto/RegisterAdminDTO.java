package es.iespuertodelacruz.jmrs.incidencias.dto;

import es.iespuertodelacruz.jmrs.incidencias.entity.Gestore;

public class RegisterAdminDTO {
	
	private String dni;
	private String nombre;
	private String apellidos;
	private String username;
	private String password;
	private int id_rol;

	public RegisterAdminDTO() {}
	
	public RegisterAdminDTO(Gestore g) {
		this.dni = g.getDni();
		this.nombre = g.getNombre();
		this.apellidos = g.getApellidos();
		/*this.username = c.getUsuario().getUsername();
		this.password = c.getUsuario().getPassword();*/
		this.id_rol = 1;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId_rol() {
		return id_rol;
	}

	public void setId_rol(int id_rol) {
		this.id_rol = id_rol;
	}	
}
