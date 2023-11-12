package es.iespuertodelacruz.jmrs.incidencias.servlets.Tickets;

import java.io.IOException;
import java.util.Scanner;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.iespuertodelacruz.jmrs.incidencias.models.daos.ClienteDAO;
import es.iespuertodelacruz.jmrs.incidencias.models.daos.GestorDAO;
import es.iespuertodelacruz.jmrs.incidencias.models.daos.TicketDAO;
import es.iespuertodelacruz.jmrs.incidencias.models.entities.Cliente;
import es.iespuertodelacruz.jmrs.incidencias.models.entities.Ticket;
import es.iespuertodelacruz.jmrs.incidencias.models.utils.GestorConexionDDBB;
import es.iespuertodelacruz.jmrs.incidencias.models.utils.Globals;

/**
 * Servlet implementation class EliminarTicket
 */
public class EliminarTicket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarTicket() {
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
		ClienteDAO clienteDao = new ClienteDAO(gc);
		GestorDAO gestorDao = new GestorDAO(gc);
		TicketDAO ticketDao = new TicketDAO(gc);
		Cliente cliente;
		Ticket ticket;
		
		String stridticket = request.getParameter("IdTicket");
		if(stridticket != null && !stridticket.trim().isEmpty()) {
			
		
		Integer idTicketElim = null;
		try {
			idTicketElim = Integer.parseInt(stridticket);
		} catch(Exception e) {
			
		}
			
		boolean estado = ticketDao.delete(idTicketElim);
			
			if(!estado) {
				request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, "El ticket " + idTicketElim + " se ha eliminado correctamente");
				request.getRequestDispatcher("tickets.jsp").forward(request, response);
			} else {
				request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, "El ticket " + idTicketElim + " No se ha eliminado");
				request.getRequestDispatcher("tickets.jsp").forward(request, response);
			}
		}else {
			request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, "Completa los campos obligatorios");
			request.getRequestDispatcher("tickets.jsp").forward(request, response);
		}
		}
}
