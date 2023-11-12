package es.iespuertodelacruz.jmrs.incidencias.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

import es.iespuertodelacruz.jmrs.incidencias.entity.Cliente;
import es.iespuertodelacruz.jmrs.incidencias.entity.Gestor;
import es.iespuertodelacruz.jmrs.incidencias.entity.SeguimientoTicket;
import es.iespuertodelacruz.jmrs.incidencias.entity.Ticket;

public class TicketDTO {

	private int idTicket;
	private String descripcion;
	private String estado;
	private Timestamp fechaInicio;
	private Timestamp fechaFin;
	private Cliente cliente;
	private Collection<String> equipoTrabajo;
	private Collection<String> seguimientoTicket;  

	public TicketDTO() {}
	
	public TicketDTO(Ticket t) {
		this.idTicket = t.getIdTicket();
		this.descripcion = t.getDescripcion();
		this.estado = t.getEstado();
		this.fechaInicio = t.getFechaInicio();
		this.fechaFin = t.getFechaFin();
		this.cliente = t.getCliente();
		
		ArrayList<String> equipoTrabajo = new ArrayList<>(); 
		for(Gestor gestor : t.getGestores()) {
			equipoTrabajo.add(gestor.getDni());
			equipoTrabajo.add(String.valueOf(t.getIdTicket()));
		}
		
		this.setEquipoTrabajo(equipoTrabajo);
		
		ArrayList<String> seguimientos = new ArrayList<>(); 
		for(SeguimientoTicket st  : t.getSeguimientoTickets()) {
			seguimientos.add(String.valueOf(st.getIdSeguimiento()));
			seguimientos.add(st.getComentario());
			seguimientos.add(st.getFecha().toString());
		}
		
		this.setSeguimientoTicket(seguimientos);
		
		
	}
	
	public Ticket toTicket() {
		Ticket t = new Ticket();
		t.setDescripcion(descripcion);
		t.setEstado(estado);
		t.setFechaInicio(fechaInicio);
		t.setFechaFin(fechaFin);
		
		return t;
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Collection<String> getEquipoTrabajo() {
		return equipoTrabajo;
	}

	public void setEquipoTrabajo(Collection<String> equipoTrabajo) {
		this.equipoTrabajo = equipoTrabajo;
	}

	public Collection<String> getSeguimientoTicket() {
		return seguimientoTicket;
	}

	public void setSeguimientoTicket(Collection<String> seguimientoTicket) {
		this.seguimientoTicket = seguimientoTicket;
	}
	
	
}
