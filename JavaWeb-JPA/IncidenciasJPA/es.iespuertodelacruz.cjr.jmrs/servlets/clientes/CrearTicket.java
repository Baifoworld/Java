package servlets.clientes;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.protobuf.TextFormat.ParseException;

import entities.Cliente;
import entities.Gestore;
import entities.Ticket;
import repositories.TicketRepository;

/**
 * Servlet implementation class CrearTicket
 */
@WebServlet({ "/CrearTicket", "/crearticket" })
public class CrearTicket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrearTicket() {
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
		//recoger sesion del cliente para añadirlo al idcliente del ticket
		Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
		// CREAR ENTITYMANAGERFACTORY
		EntityManagerFactory emf = (EntityManagerFactory) request.getServletContext().getAttribute("emf");
		Ticket ticket = null;
		TicketRepository ticketR = new TicketRepository(emf);
		Gestore gestor = null;
		List<Gestore> equipoTrabajo = new ArrayList<Gestore>();
		
		String strFechaInicio = request.getParameter("fechaInicio");
		Date fechaInicio = null;
		
		if(strFechaInicio != null) {
			try {
				Date fechaini = new SimpleDateFormat("dd/MM/yyyy").parse(strFechaInicio);
				fechaInicio = new Date(fechaini.getTime());
			}catch(Exception ex) {
				
			}
		}
		
		String strFechaFin = request.getParameter("fechaFin");
		Date fechaFin = null;
		
		if(strFechaFin != null) {
			try {
				Date fechafin = new SimpleDateFormat("dd/MM/yyyy").parse(strFechaFin);
				fechaFin = new Date(fechafin.getTime());
			}catch(Exception ex) {
				
			}
		}
		
	}

}
