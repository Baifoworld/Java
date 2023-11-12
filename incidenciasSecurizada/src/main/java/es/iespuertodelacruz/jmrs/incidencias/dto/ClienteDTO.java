package es.iespuertodelacruz.jmrs.incidencias.dto;

import es.iespuertodelacruz.jmrs.incidencias.entity.Cliente;

public class ClienteDTO {
	
	private String id_cliente;
	private String nombre;
	private String direccion;
	private String telefono;

	public ClienteDTO() {}
	
	public ClienteDTO(Cliente c) {
		this.nombre = c.getNombre();
		this.direccion = c.getDireccion();
		this.telefono = c.getTelefono();
	}
	 
	public Cliente toCliente() {
		Cliente cliente = new Cliente();
		cliente.setNombre(nombre);
		cliente.setDireccion(direccion);
		cliente.setTelefono(telefono);
		
		return cliente;
	}
	
	public String getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(String id_cliente) {
		this.id_cliente = id_cliente;
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

}
