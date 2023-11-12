package es.iespuertodelacruz.jmrs.incidencias.servlets.Tickets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
import es.iespuertodelacruz.jmrs.incidencias.models.entities.Gestor;
import es.iespuertodelacruz.jmrs.incidencias.models.entities.Ticket;
import es.iespuertodelacruz.jmrs.incidencias.models.utils.GestorConexionDDBB;
import es.iespuertodelacruz.jmrs.incidencias.models.utils.Globals;

/**
 * Servlet implementation class AgregarTicket
 */
public class AgregarTicket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgregarTicket() {
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
		Gestor gestor;
		Ticket create = null;
		ArrayList<Gestor> equipoTrabajo = new ArrayList<>();
		
		String strfechaInicio = request.getParameter("fechaInicio");
		Date fechaInicio = null;
		if(strfechaInicio != null) {
			try {
				Date fechaini = new SimpleDateFormat("dd/MM/yyyy").parse(strfechaInicio);
				fechaInicio = new Date(fechaini.getTime());
			} catch(ParseException e) {
				
			}
			String strfechaFin = request.getParameter("fechaFin");
			Date fechaFin = null;
			if(strfechaFin != null) {
				try {
					Date fechafin = new SimpleDateFormat("dd/MM/yyyy").parse(strfechaFin);
					fechaFin = new Date(fechafin.getTime());
				} catch(ParseException e) {
					
				}
			}
			String descripcion = request.getParameter("descripcion");
			String estado = request.getParameter("estado");
			String idCliente = request.getParameter("idCliente");
			cliente = clienteDao.findById(idCliente);
			String dnigestor = request.getParameter("dniGestor");
			String[] gestores = dnigestor.split(",");
			for(int i = 0; i < gestores.length ; i++) {
				gestor = gestorDao.findById(gestores[i]);
				if(gestor != null)
					equipoTrabajo.add(gestor);
			}
			
			if(fechaFin != null) {
				ticket = new Ticket(fechaInicio, descripcion, estado, cliente, equipoTrabajo);
				create = ticketDao.save(ticket);
				if(create != null) {
					request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, "El ticket se ha creado correctamente");
				} else
					request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, "El ticket no se ha podido crear");
				request.getRequestDispatcher("tickets.jsp").forward(request, response);
			} else {
				ticket = new Ticket(fechaInicio, fechaFin, descripcion, estado, cliente, equipoTrabajo);
				create = ticketDao.save(ticket);
				if(create != null) {
					request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, "El ticket se ha creado correctamente");
				} else
					request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, "El ticket no se ha podido crear");
				request.getRequestDispatcher("tickets.jsp").forward(request, response);
			}	
		}		
	}

}
