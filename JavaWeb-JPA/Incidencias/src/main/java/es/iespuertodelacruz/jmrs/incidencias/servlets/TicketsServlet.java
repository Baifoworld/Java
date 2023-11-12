package es.iespuertodelacruz.jmrs.incidencias.servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.iespuertodelacruz.jmrs.incidencias.models.daos.TicketDAO;
import es.iespuertodelacruz.jmrs.incidencias.models.utils.GestorConexionDDBB;

/**
 * Servlet implementation class Tickets
 */
@WebServlet({ "/Tickets", "/tickets" })
public class TicketsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TicketsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("tickets.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = request.getServletContext();
		//HttpSession session = request.getSession();
		GestorConexionDDBB gc = new GestorConexionDDBB("incidencias", "root", "1q2w3e4r");
		TicketDAO ticketDao = new TicketDAO(gc);
		
		String submit = request.getParameter("submit");
		
		switch(submit) {
			case "Crear":
				CrearTicket(request, response, ticketDao);
				break;
			case "Modificar":
				ModificarTicket(request, response, ticketDao);
				break;
			case "Eliminar":
				EliminarTicket(request, response, ticketDao);
				break;
			case "Mostrar":
				MostrarTicket(request, response, ticketDao);
				break;
		}
		doGet(request, response);
	}
	
	private void CrearTicket(HttpServletRequest request, HttpServletResponse response, TicketDAO ticketDao) {
		
	}
	
	private void ModificarTicket(HttpServletRequest request, HttpServletResponse response, TicketDAO ticketDao) {
		
	}
	
	private void EliminarTicket(HttpServletRequest request, HttpServletResponse response, TicketDAO ticketDao) {
		
	}
	
	private void MostrarTicket(HttpServletRequest request, HttpServletResponse response, TicketDAO ticketDao) {
		
	}

}
