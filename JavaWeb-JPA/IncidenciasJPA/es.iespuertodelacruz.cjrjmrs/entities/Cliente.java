package entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the Clientes database table.
 * 
 */
@Entity
@Table(name="Clientes")
@NamedQuery(name="Cliente.findAll", query="SELECT c FROM Cliente c")
@NamedQuery(name="Cliente.findByName", query="SELECT c FROM Cliente c WHERE c.nick= :nick")
@NamedQuery(name="Cliente.findById", query="SELECT c FROM Cliente c WHERE c.id= :id")
@NamedQuery(name="Cliente.save", query="INSERT INTO Cliente c (id_cliente, nick, nombre, direccion, telefono) VALUES (c.id_cliente, c.nick, c.nombre, c.direccion, c.telefono")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_cliente", unique=true, nullable=false, length=9)
	private String idCliente;

	@Column(nullable=false, length=250)
	private String direccion;

	@Column(nullable=false, length=100)
	private String nombre;

	@Column(length=15)
	private String telefono;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="nick", nullable=false)
	private Usuario usuario;

	//bi-directional many-to-one association to Ticket
	@OneToMany(mappedBy="cliente")
	private Set<Ticket> tickets;

	public Cliente() {
	}

	public String getIdCliente() {
		return this.idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
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

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Set<Ticket> getTickets() {
		return this.tickets;
	}

	public void setTickets(Set<Ticket> tickets) {
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