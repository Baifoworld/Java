package es.iespuertodelacruz.jmrs.incidencias.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the tickets database table.
 * 
 */
@Entity
@Table(name="tickets")
@NamedQuery(name="Ticket.findAll", query="SELECT t FROM Ticket t")
public class Ticket implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_ticket")
	private int idTicket;

	private String descripcion;

	private String estado;

	@Column(name="fecha_fin")
	private Timestamp fechaFin;

	@Column(name="fecha_inicio")
	private Timestamp fechaInicio;

	//bi-directional many-to-many association to Gestore
	
	@ManyToMany
	@JoinTable(
		name="equipos_trabajo"
		, joinColumns={
			@JoinColumn(name="id_ticket")
			}
		, inverseJoinColumns={
			@JoinColumn(name="dni")
			}
		)
	private Set<Gestor> gestores;

	//bi-directional many-to-one association to SeguimientoTicket
	@JsonIgnore
	@OneToMany(mappedBy="ticket")
	private List<SeguimientoTicket> seguimientoTickets;

	//bi-directional many-to-one association to Cliente
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="id_cliente")
	private Cliente cliente;

	public Ticket() {
	}

	public int getIdTicket() {
		return this.idTicket;
	}

	public void setIdTicket(int idTicket) {
		this.idTicket = idTicket;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Timestamp getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Timestamp fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Timestamp getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Timestamp fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Set<Gestor> getGestores() {
		return this.gestores;
	}

	public void setGestores(Set<Gestor> gestores) {
		this.gestores = gestores;
	}

	public List<SeguimientoTicket> getSeguimientoTickets() {
		return this.seguimientoTickets;
	}

	public void setSeguimientoTickets(List<SeguimientoTicket> seguimientoTickets) {
		this.seguimientoTickets = seguimientoTickets;
	}

	public SeguimientoTicket addSeguimientoTicket(SeguimientoTicket seguimientoTicket) {
		getSeguimientoTickets().add(seguimientoTicket);
		seguimientoTicket.setTicket(this);

		return seguimientoTicket;
	}

	public SeguimientoTicket removeSeguimientoTicket(SeguimientoTicket seguimientoTicket) {
		getSeguimientoTickets().remove(seguimientoTicket);
		seguimientoTicket.setTicket(null);

		return seguimientoTicket;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}