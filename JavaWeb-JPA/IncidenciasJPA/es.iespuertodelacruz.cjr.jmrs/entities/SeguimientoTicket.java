package entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the SeguimientoTickets database table.
 * 
 */
@Entity
@Table(name="SeguimientoTickets")
@NamedQuery(name="SeguimientoTicket.findAll", query="SELECT s FROM SeguimientoTicket s")
public class SeguimientoTicket implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_seguimiento", unique=true, nullable=false)
	private int idSeguimiento;

	@Column(nullable=false, length=500)
	private String comentario;

	@Column(nullable=false)
	private Timestamp fecha;

	//bi-directional many-to-one association to Gestore
	@ManyToOne
	@JoinColumn(name="dni", nullable=false)
	private Gestore gestore;

	//bi-directional many-to-one association to Ticket
	@ManyToOne
	@JoinColumn(name="id_ticket", nullable=false)
	private Ticket ticket;

	public SeguimientoTicket() {
	}

	public int getIdSeguimiento() {
		return this.idSeguimiento;
	}

	public void setIdSeguimiento(int idSeguimiento) {
		this.idSeguimiento = idSeguimiento;
	}

	public String getComentario() {
		return this.comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Timestamp getFecha() {
		return this.fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public Gestore getGestore() {
		return this.gestore;
	}

	public void setGestore(Gestore gestore) {
		this.gestore = gestore;
	}

	public Ticket getTicket() {
		return this.ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

}