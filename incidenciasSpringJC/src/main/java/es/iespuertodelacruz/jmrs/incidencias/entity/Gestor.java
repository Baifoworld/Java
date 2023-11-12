package es.iespuertodelacruz.jmrs.incidencias.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the gestores database table.
 * 
 */
@Entity
@Table(name="gestores")
@NamedQuery(name="Gestor.findAll", query="SELECT g FROM Gestor g")
public class Gestor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=9)
	private String dni;

	@Column(length=250)
	private String apellidos;

	@Column(nullable=false, length=100)
	private String nombre;

	//bi-directional many-to-many association to Ticket
	
	@ManyToMany(mappedBy="gestores")
	private List<Ticket> tickets;

	//bi-directional many-to-one association to SeguimientoTicket
	
	@OneToMany(mappedBy="gestore")
	private List<SeguimientoTicket> seguimientoTickets;

	public Gestor() {
	}

	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Ticket> getTickets() {
		return this.tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public List<SeguimientoTicket> getSeguimientoTickets() {
		return this.seguimientoTickets;
	}

	public void setSeguimientoTickets(List<SeguimientoTicket> seguimientoTickets) {
		this.seguimientoTickets = seguimientoTickets;
	}

	public SeguimientoTicket addSeguimientoTicket(SeguimientoTicket seguimientoTicket) {
		getSeguimientoTickets().add(seguimientoTicket);
		seguimientoTicket.setGestore(this);

		return seguimientoTicket;
	}

	public SeguimientoTicket removeSeguimientoTicket(SeguimientoTicket seguimientoTicket) {
		getSeguimientoTickets().remove(seguimientoTicket);
		seguimientoTicket.setGestore(null);

		return seguimientoTicket;
	}

}