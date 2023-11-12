package es.iespuertodelacruz.jmrs.incidencias.dto;

import es.iespuertodelacruz.jmrs.incidencias.entity.Cliente;
import es.iespuertodelacruz.jmrs.incidencias.entity.Usuario;

public class RegisterDTO {
	
	private String idCliente;
	private String nombre;
	private String direccion;
	private String telefono;
	private String username;
	private String password;
	private int id_rol;

	public RegisterDTO() {}
	/*
	public RegisterDTO(Cliente c) {
		this.idCliente = c.getIdCliente();
		this.nombre = c.getNombre();
		this.direccion = c.getDireccion();
		this.telefono = c.getTelefono();
		/*this.username = c.getUsuario().getUsername();
		this.password = c.getUsuario().getPassword();**
		this.id_rol = 2;
	}*/

	public String getidCliente() {
		return idCliente;
	}

	public void setIdcliente(String id_cliente) {
		this.idCliente = id_cliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
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
