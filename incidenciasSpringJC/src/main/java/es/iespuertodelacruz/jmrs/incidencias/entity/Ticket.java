package es.iespuertodelacruz.jmrs.incidencias.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int idticket;

	@Column(nullable=false, length=500)
	private String descripcion;

	@Column(nullable=false, length=50)
	private String estado;

	@Column(name="fecha_fin")
	private Timestamp fechaFin;

	@Column(name="fecha_inicio", nullable=false)
	private Timestamp fechaInicio;

	//bi-directional many-to-many association to Gestor
	@ManyToMany
	@JoinTable(
		name="equipos_trabajo"
		, joinColumns={
			@JoinColumn(name="idticket", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="dni", nullable=false)
			}
		)
	private List<Gestor> gestores;

	//bi-directional many-to-one association to SeguimientoTicket
	@OneToMany(mappedBy="ticket")
	private List<SeguimientoTicket> seguimientoTickets;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="idcliente", nullable=false)
	private Cliente cliente;

	public Ticket() {
	}

	public int getIdticket() {
		return this.idticket;
	}

	public void setIdticket(int idticket) {
		this.idticket = idticket;
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

	public List<Gestor> getGestores() {
		return this.gestores;
	}

	public void setGestores(List<Gestor> gestores) {
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