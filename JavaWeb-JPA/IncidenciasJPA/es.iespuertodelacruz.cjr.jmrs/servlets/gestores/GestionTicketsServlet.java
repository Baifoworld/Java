package servlets.gestores;

import java.io.IOException;
import java.util.ArrayList;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import entities.Gestore;
import entities.Ticket;
import repositories.ClienteRepository;
import repositories.GestorRepository;
import repositories.TicketRepository;
import utils.Globals;

/**
 * Servlet implementation class GestionTicketsServlet
 */
@WebServlet("/GestionTicketsServlet")
public class GestionTicketsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionTicketsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idCliente = request.getParameter("idCliente");
		String strFechaInicio = request.getParameter("fechaInicio");
		String strFechaFin = request.getParameter("fechaFin");
		String descripcion = request.getParameter("descripcion");
		String estado = request.getParameter("estado");
		String opciones = request.getParameter("opciones");
		
		// CREAR ENTITYMANAGERFACTORY
		EntityManagerFactory emf = (EntityManagerFactory) request.getServletContext().getAttribute("emf");
		ClienteRepository clienteR = new ClienteRepository(emf);
		TicketRepository ticketR = new TicketRepository(emf);
		Ticket ticket = null;
		
		switch(opciones) {
			case "Crear Ticket":
				if(idCliente != null && !idCliente.trim().isEmpty() &&
						strFechaInicio != null && !strFechaInicio.trim().isEmpty() &&
						descripcion != null && !descripcion.trim().isEmpty() &&
						estado != null && !estado.trim().isEmpty()){
							
				} else {
					request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, "Debes de rellenar los campos obligatorios"); 
				}

				break;
			case "Buscar Ticket":
				String strIdTicket = request.getParameter("idTicket");
				if(strIdTicket != null && !strIdTicket.trim().isEmpty()) {
					Integer idTicket = Integer.parseInt(strIdTicket);
					ArrayList<Ticket> tickets = new ArrayList<Ticket>();
					ticket = ticketR.findById(idTicket);
					
					if(ticket != null) {
						tickets.add(ticket);
						ObjectMapper mapper = new ObjectMapper();
						String mostrarTicket = mapper
							.writerWithDefaultPrettyPrinter()
							.writeValueAsString(ticket.toString());
							
						request.setAttribute("ticket",tickets);
						request.getRequestDispatcher("mostrartickets.jsp").forward(request, response);
					}
				}else {
					request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, "No has añadido un Id Ticket correcto"); 
				}
				
				break;
			case "Eliminar Ticket":
				
				break;
			case "Editar Ticket":
				break;	
			case "Ver Tickets":
				
				ArrayList<Ticket> tickets = new ArrayList<Ticket>();
				tickets = (ArrayList<Ticket>) ticketR.findAll();
				
				if(tickets != null) {
					ObjectMapper mapper = new ObjectMapper();
					String mostrarTicket = mapper
					.writerWithDefaultPrettyPrinter()
					.writeValueAsString(tickets.toString());
					
					request.setAttribute("tickets",tickets);
					request.getRequestDispatcher("mostrartickets.jsp").forward(request, response);
				}
				break;
		}
	}
}
