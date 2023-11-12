package es.iespuertodelacruz.jmrs.incidencias.models.entities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Ticket {
	
	/**
	 * Variables de Ticket
	 */
	private int idTicket;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	Date fechaInicio;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	Date fechaFin;
	private String descripcion;
	private String estado;
	
	@JsonIgnore
	Cliente cliente;
	
	@JsonIgnore
	ArrayList<Gestor> equipoTrabajo;
	
	

	/**
	 * Constructor por defecto
	 */
	public Ticket() {
		
	}
	
	/**
	 * COnstructor con todas las variables
	 * @param idTicket
	 * @param fechaInicio
	 * @param fechaFin
	 * @param descripcion
	 * @param estado
	 * @param idCliente
	 * @param equipoTrabajo
	 */
	public Ticket(int idTicket, Date fechaInicio, Date fechaFin, String descripcion, String estado, Cliente cliente, ArrayList<Gestor> gestores) {
		super();
		this.idTicket = idTicket;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.descripcion = descripcion;
		this.estado = estado;
		this.cliente = cliente;
		this.equipoTrabajo = gestores; 
	}
	
	/**
	 * COnstructor sin clientes
	 * @param idTicket
	 * @param fechaInicio
	 * @param fechaFin
	 * @param descripcion
	 * @param estado
	 * @param equipoTrabajo
	 */
	public Ticket(int idTicket, Date fechaInicio, Date fechaFin, String descripcion, String estado, ArrayList<Gestor> gestores) {
		super();
		this.idTicket = idTicket;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.descripcion = descripcion;
		this.estado = estado;
		this.equipoTrabajo = gestores; 
	}
	
	/**
	 * COnstructor con todas las variables excepto equipoTrabajo
	 * @param idTicket
	 * @param fechaInicio
	 * @param fechaFin
	 * @param descripcion
	 * @param estado
	 * @param idCliente
	 * @param equipoTrabajo
	 */
	public Ticket(int idTicket, Date fechaInicio, Date fechaFin, String descripcion, String estado, Cliente cliente) {
		super();
		this.idTicket = idTicket;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.descripcion = descripcion;
		this.estado = estado;
		this.cliente = cliente;
	}
	
	/**
	 * Constructor para idTicket autoincremental
	 * @param fechaInicio
	 * @param fechaFin
	 * @param descripcion
	 * @param estado
	 * @param cliente
	 * @param equipoTrabajo
	 */
	public Ticket(Date fechaInicio, Date fechaFin, String descripcion, String estado, Cliente cliente, ArrayList<Gestor> equipoTrabajo) {
		super();
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.descripcion = descripcion;
		this.estado = estado;
		this.cliente = cliente;
		this.equipoTrabajo = equipoTrabajo;
	}
	
	/**
	 * Constructor para idTicket autoincremental y sin FEcha fin
	 * @param fechaInicio
	 * @param fechaFin
	 * @param descripcion
	 * @param estado
	 * @param cliente
	 * @param equipoTrabajo
	 */
	public Ticket(Date fechaInicio, String descripcion, String estado, Cliente cliente, ArrayList<Gestor> equipoTrabajo) {
		super();
		this.fechaInicio = fechaInicio;
		this.descripcion = descripcion;
		this.estado = estado;
		this.cliente = cliente;
		this.equipoTrabajo = equipoTrabajo;
	}

	//Comienzo getters and setters
	
	
	public int getIdTicket() {
		return idTicket;
	}
	
	public void setIdTicket(int idTicket) {
		this.idTicket = idTicket;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public ArrayList<Gestor> getEquipoTrabajo() {
		return equipoTrabajo;
	}

	public void setEquipoTrabajo(ArrayList<Gestor> equipoTrabajo) {
		this.equipoTrabajo = equipoTrabajo;
	}
	//Fin getters and setters
	
	/**
	 * Devuelve la fecha en formato normal de FechaInicio
	 * @return FechaInicio en formato normal (String)
	 */
	public String getNormalFechaInicio() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM//yyyy");
		return sdf.format(fechaInicio);
	}
	
	/**
	 * Devuelve la fecha en formato normal de FechaFin
	 * @return FechaFin en formato normal (String)
	 */
	public String getNormalFechaFin() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM//yyyy");
		return sdf.format(fechaFin);
	}
}
