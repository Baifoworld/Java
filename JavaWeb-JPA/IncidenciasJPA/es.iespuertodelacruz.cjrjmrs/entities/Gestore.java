package entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the Gestores database table.
 * 
 */
@Entity
@Table(name="Gestores")
@NamedQuery(name="Gestore.findAll", query="SELECT g FROM Gestore g")
public class Gestore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=9)
	private String dni;

	@Column(length=250)
	private String apellidos;

	@Column(nullable=false, length=100)
	private String nombre;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="nick", nullable=false)
	private Usuario usuario;

	//bi-directional many-to-one association to SeguimientoTicket
	@OneToMany(mappedBy="gestore")
	private Set<SeguimientoTicket> seguimientoTickets;

	//bi-directional many-to-many association to Ticket
	@ManyToMany(mappedBy="gestores")
	private Set<Ticket> tickets;

	public Gestore() {
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

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Set<SeguimientoTicket> getSeguimientoTickets() {
		return this.seguimientoTickets;
	}

	public void setSeguimientoTickets(Set<SeguimientoTicket> seguimientoTickets) {
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

	public Set<Ticket> getTickets() {
		return this.tickets;
	}

	public void setTickets(Set<Ticket> tickets) {
		this.tickets = tickets;
	}

}