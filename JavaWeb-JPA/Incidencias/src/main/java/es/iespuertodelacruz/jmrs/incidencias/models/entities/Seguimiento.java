package es.iespuertodelacruz.jmrs.incidencias.models.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Seguimiento {

	/**
	 * Variables de Seguimiento
	 */
	
	private int idSeguimiento;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date fecha;
	private String comentario;
	
	@JsonIgnore
	private Gestor gestor;
	
	@JsonIgnore
	private Ticket ticket;
	
	/**
	 * Constructor por defecto
	 */
	public Seguimiento() {
		
	}
	
	/**
	 * COnstructor con variables excepto idSeguimiento
	 * @param idTicket
	 * @param fecha
	 * @param comentario
	 * @param dniGestor
	 */
	public Seguimiento(Date fecha, String comentario, Gestor gestor, Ticket ticket) {
		super();
		
		this.idSeguimiento = idSeguimiento;
		this.fecha = fecha;
		this.comentario = comentario;
		this.ticket = ticket;
		this.gestor = gestor;
	}
	
	/**
	 * COnstructor con todas las variables
	 * @param idTicket
	 * @param idSeguimiento
	 * @param fecha
	 * @param comentario
	 * @param dniGestor
	 */
	public Seguimiento(int idSeguimiento, Date fecha, String comentario, Gestor gestor, Ticket ticket) {
		super();
		
		this.idSeguimiento = idSeguimiento;
		this.fecha = fecha;
		this.comentario = comentario;
		this.ticket = ticket;
		this.gestor = gestor;
	}

	//Comienzo getters and setters

	public int getIdSeguimiento() {
		return idSeguimiento;
	}

	public void setIdSeguimiento(int idSeguimiento) {
		this.idSeguimiento = idSeguimiento;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Gestor getGestor() {
		return gestor;
	}

	public void setGestor(Gestor gestor) {
		this.gestor = gestor;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	//FIn getters and setters
	
	/**
	 * Devuelve la fecha en formato normal de FEcha
	 * @return Fecha en formato normal (String)
	 */
	public String getNormalFecha() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM//yyyy");
		return sdf.format(fecha);
	}
}
