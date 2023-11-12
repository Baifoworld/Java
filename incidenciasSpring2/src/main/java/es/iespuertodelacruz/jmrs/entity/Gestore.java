package es.iespuertodelacruz.jmrs.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the gestores database table.
 * 
 */
@Entity
@Table(name="gestores")
@NamedQuery(name="Gestore.findAll", query="SELECT g FROM Gestore g")
public class Gestore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String dni;

	private String apellidos;

	private String nombre;

	//bi-directional many-to-many association to Ticket
	@ManyToMany(mappedBy="gestores")
	private List<Ticket> tickets;

	//bi-directional many-to-one association to SeguimientoTicket
	@OneToMany(mappedBy="gestore")
	private List<SeguimientoTicket> seguimientoTickets;

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