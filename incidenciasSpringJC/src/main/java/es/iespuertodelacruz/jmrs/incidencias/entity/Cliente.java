package es.iespuertodelacruz.jmrs.incidencias.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the clientes database table.
 * 
 */
@Entity
@Table(name="clientes")
@NamedQuery(name="Cliente.findAll", query="SELECT c FROM Cliente c")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=9)
	private String idcliente;

	@Column(length=250)
	private String direccion;

	@Column(nullable=false, length=100)
	private String nombre;

	@Column(length=15)
	private String telefono;

	//bi-directional many-to-one association to Ticket
	@JsonIgnore
	@OneToMany(mappedBy="cliente")
	private List<Ticket> tickets;

	public Cliente() {
	}

	public String getIdcliente() {
		return this.idcliente;
	}

	public void setIdcliente(String idcliente) {
		this.idcliente = idcliente;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<Ticket> getTickets() {
		return this.tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Ticket addTicket(Ticket ticket) {
		getTickets().add(ticket);
		ticket.setCliente(this);

		return ticket;
	}

	public Ticket removeTicket(Ticket ticket) {
		getTickets().remove(ticket);
		ticket.setCliente(null);

		return ticket;
	}

}