package es.iespuertodelacruz.jmrs.incidencias.dto;

import java.sql.Timestamp;

import es.iespuertodelacruz.jmrs.incidencias.entity.SeguimientoTicket;

public class SeguimientoTicketPostDTO {

	private String comentario;
	private Timestamp fecha;
	private String dniGestor;
	private int idTicket;
	
	public SeguimientoTicketPostDTO() {
		
	}
	
	public SeguimientoTicketPostDTO(SeguimientoTicket st) {
		this.comentario = st.getComentario();
		this.fecha = st.getFecha();
		this.dniGestor = st.getGestore().getDni();
		this.idTicket = st.getTicket().getIdTicket();
		
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

	public String getDniGestor() {
		return dniGestor;
	}

	public void setDniGestor(String dniGestor) {
		this.dniGestor = dniGestor;
	}

	public int getIdTicket() {
		return idTicket;
	}

	public void setIdTicket(int idTicket) {
		this.idTicket = idTicket;
	}
}
