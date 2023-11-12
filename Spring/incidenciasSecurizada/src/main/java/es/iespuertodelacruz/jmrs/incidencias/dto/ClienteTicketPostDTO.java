package es.iespuertodelacruz.jmrs.incidencias.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

import es.iespuertodelacruz.jmrs.incidencias.entity.Gestore;
import es.iespuertodelacruz.jmrs.incidencias.entity.Ticket;

public class ClienteTicketPostDTO {
	
	private int idTicket;
	private String descripcion;
	private String estado;
	private Timestamp fechaInicio;
	private Timestamp fechaFin;

	public ClienteTicketPostDTO() {}
	
	public ClienteTicketPostDTO(Ticket t) {
		
		this.descripcion = t.getDescripcion();
		this.estado = t.getEstado();
		this.fechaInicio = t.getFechaInicio();
		this.fechaFin = t.getFechaFin();
	}

	
	public int getIdTicket() {
		return idTicket;
	}

	public void setIdTicket(int idTicket) {
		this.idTicket = idTicket;
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

	public Timestamp getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Timestamp fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Timestamp getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Timestamp fechaFin) {
		this.fechaFin = fechaFin;
	}	

}