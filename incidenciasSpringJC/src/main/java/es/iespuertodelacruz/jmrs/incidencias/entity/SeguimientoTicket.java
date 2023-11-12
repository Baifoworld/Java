package es.iespuertodelacruz.jmrs.incidencias.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the seguimiento_ticket database table.
 * 
 */
@Entity
@Table(name="seguimiento_ticket")
@NamedQuery(name="SeguimientoTicket.findAll", query="SELECT s FROM SeguimientoTicket s")
public class SeguimientoTicket implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int idseguimientoticket;

	@Column(nullable=false, length=500)
	private String comentario;

	@Column(nullable=false)
	private Timestamp fecha;

	//bi-directional many-to-one association to Gestor
	@ManyToOne
	@JoinColumn(name="dnigestor", nullable=false)
	private Gestor gestore;

	//bi-directional many-to-one association to Ticket
	@ManyToOne
	@JoinColumn(name="idticket", nullable=false)
	private Ticket ticket;

	public SeguimientoTicket() {
	}

	public int getIdseguimientoticket() {
		return this.idseguimientoticket;
	}

	public void setIdseguimientoticket(int idseguimientoticket) {
		this.idseguimientoticket = idseguimientoticket;
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

	public Gestor getGestore() {
		return this.gestore;
	}

	public void setGestore(Gestor gestore) {
		this.gestore = gestore;
	}

	public Ticket getTicket() {
		return this.ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

}