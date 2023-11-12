package es.iespuertodelacruz.jmrs.incidencias.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

import es.iespuertodelacruz.jmrs.incidencias.entity.Cliente;
import es.iespuertodelacruz.jmrs.incidencias.entity.Gestore;
import es.iespuertodelacruz.jmrs.incidencias.entity.SeguimientoTicket;
import es.iespuertodelacruz.jmrs.incidencias.entity.Ticket;

public class TicketPostDTO {

	private int idTicket;
	private String descripcion;
	private String estado;
	private Timestamp fechaInicio;
	private Timestamp fechaFin;
	private String idCliente;
	private Collection<String> gestores;
	private Collection<String> seguimientoTicket;  

	public TicketPostDTO() {}
	
	public TicketPostDTO(Ticket t) {
		
		this.descripcion = t.getDescripcion();
		this.estado = t.getEstado();
		this.fechaInicio = t.getFechaInicio();
		this.fechaFin = t.getFechaFin();
		this.idCliente = t.getCliente().getIdCliente();
		
		ArrayList<String> equipoTrabajo = new ArrayList<>(); 
		for(Gestore gestor : t.getGestores()) {
			equipoTrabajo.add(gestor.getDni());
		}
		
		this.gestores = equipoTrabajo;		
		
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

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public Collection<String> getEquipoTrabajo() {
		return gestores;
	}

	public void setEquipoTrabajo(Collection<String> equipoTrabajo) {
		this.gestores = equipoTrabajo;
	}

	public Collection<String> getSeguimientoTicket() {
		return seguimientoTicket;
	}

	public void setSeguimientoTicket(Collection<String> seguimientoTicket) {
		this.seguimientoTicket = seguimientoTicket;
	}
	
	
}
