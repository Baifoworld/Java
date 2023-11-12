package es.iespuertodelacruz.jmrs.incidencias.servlets.Tickets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.iespuertodelacruz.jmrs.incidencias.models.daos.TicketDAO;
import es.iespuertodelacruz.jmrs.incidencias.models.entities.Seguimiento;
import es.iespuertodelacruz.jmrs.incidencias.models.entities.Ticket;
import es.iespuertodelacruz.jmrs.incidencias.models.utils.GestorConexionDDBB;
import es.iespuertodelacruz.jmrs.incidencias.models.utils.Globals;

/**
 * Servlet implementation class MostrarTicket
 */
public class MostrarTicket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MostrarTicket() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = request.getServletContext();
		HttpSession session = request.getSession();
		GestorConexionDDBB gc = new GestorConexionDDBB("incidencias","root","1q2w3e4r");
		TicketDAO ticketDao = new TicketDAO(gc);
		Ticket ticket = null;
		ArrayList<Ticket> ticketLista = new ArrayList<Ticket>();
		ObjectMapper mapper = new ObjectMapper();
		
		String stridTicket = request.getParameter("idTicket");
		Integer idTicket = null;
		if(stridTicket != null && !stridTicket.trim().isEmpty()) {
			idTicket = Integer.parseInt(stridTicket);
		} else {
			request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, "Debes de añadir un ID de Ticket");
			request.getRequestDispatcher("tickets.jsp").forward(request, response);
		}
		
		ticket = ticketDao.findById(idTicket);
		if(ticket != null) {
			
			if(ticket.getFechaInicio() != null && ticket.getFechaFin() != null) {
				request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, ticket.getIdTicket()+ "\n"
						+ ticket.getNormalFechaInicio() + "\n"
						+ticket.getNormalFechaFin() + "\n"
						+ticket.getDescripcion() + "\n"
						+ticket.getEstado() + "\n"
						+ticket.getCliente().getIdCliente() + "\n");
			}
			else if(ticket.getFechaInicio() == null) {
				request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, ticket.getIdTicket()+ "\n"
						+ "Fecha de Inicio nula " + "\n"
						+ticket.getNormalFechaFin() + "\n"
						+ticket.getDescripcion() + "\n"
						+ticket.getEstado() + "\n"
						+ticket.getCliente().getIdCliente() + "\n");
			}
			else if(ticket.getFechaFin() == null) {
				request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, ticket.getIdTicket()+ "\n"
						+ ticket.getNormalFechaInicio() + "\n"
						+ "Fecha fin nula" + "\n"
						+ticket.getDescripcion() + "\n"
						+ticket.getEstado() + "\n"
						+ticket.getCliente().getIdCliente() + "\n");
			}
			
			try {
				ticketLista = ticketDao.selectAll();
				String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(ticketLista);
				request.getSession().setAttribute(Globals.JSON, "Todos los Tickets:\n" + json);
			} catch(JsonProcessingException e) {
				request.getSession().setAttribute(Globals.JSON, "Error al generar el Json");
			}
			
			request.getRequestDispatcher("tickets.jsp").forward(request, response);
		} else {
			request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, "No has introducido un ID correcto (no existe el Ticket");
			request.getRequestDispatcher("tickets.jsp").forward(request, response);
		}
	}

}
