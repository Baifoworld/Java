package es.iespuertodelacruz.jmrs.incidencias.models.entities;

public class Cliente {

	/**
	 * Variables de Cliente
	 */
	private String idCliente;
	private String nombreContacto;
	private String direccion;
	private String telefono;
	
	/**
	 * constructor por defecto
	 * 
	 */
	public Cliente() {
		
	}
	
	/**
	 * Constructor con todas las variables
	 * @param idCliente
	 * @param nombreContacto
	 * @param direccion
	 * @param telefono
	 */
	public Cliente(String idCliente, String nombreContacto, String direccion, String telefono) {
		super();
		this.idCliente = idCliente;
		this.nombreContacto = nombreContacto;
		this.direccion = direccion;
		this.telefono = telefono;
	}

	//Comienzo getters and setters
	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombreContacto() {
		return nombreContacto;
	}

	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
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
	//Fin getters and setters
}
