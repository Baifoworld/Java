package es.iespuertodelacruz.jmrs.incidencias.dto;

import java.sql.Timestamp;

import es.iespuertodelacruz.jmrs.incidencias.entity.SeguimientoTicket;

public class SeguimientoTicketDTO {

	private String comentario;
	private Timestamp fecha;
	
	public SeguimientoTicketDTO() {
		
	}
	
	public SeguimientoTicketDTO(SeguimientoTicket st) {
		this.comentario = st.getComentario();
		this.fecha = st.getFecha();
	}
	
	public SeguimientoTicket toSeguiTicket() {
		SeguimientoTicket st = new SeguimientoTicket();
		st.setComentario(comentario);
		st.setFecha(fecha);

		return st;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}
	
	
}
