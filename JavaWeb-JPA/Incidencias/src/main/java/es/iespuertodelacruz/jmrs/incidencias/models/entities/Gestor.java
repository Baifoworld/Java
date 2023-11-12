package es.iespuertodelacruz.jmrs.incidencias.models.entities;

public class Gestor {

	/**
	 * Variables de Gestor
	 */
	private String DNI;
	private String nombre;
	private String apellidos;
	
	/**
	 * Constructor por defecto
	 */
	public Gestor() {
		
	}
	
	/**
	 * Constructor con todas las variables
	 * @param dni
	 * @param nombre
	 * @param apellidos
	 */
	public Gestor(String dni, String nombre, String apellidos) {
		super();
		DNI = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
	}

	//Comienzo getters and setters
	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dni) {
		DNI = dni;
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
	//FIn getters and setters
}
