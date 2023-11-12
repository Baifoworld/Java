package es.iespuertodelacruz.jmrs.incidencias.servlets.Tickets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import es.iespuertodelacruz.jmrs.incidencias.models.entities.Gestor;
import es.iespuertodelacruz.jmrs.incidencias.models.entities.Ticket;
import es.iespuertodelacruz.jmrs.incidencias.models.utils.GestorConexionDDBB;
import es.iespuertodelacruz.jmrs.incidencias.models.utils.Globals;

/**
 * Servlet implementation class ModificarTicket
 */
public class ModificarTicket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificarTicket() {
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
		Cliente cliente = null;
		Ticket ticket;
		Gestor gestor;
		ArrayList<Gestor> equipoTrabajo = new ArrayList<>();
		boolean ok = false;

		String stridModi = request.getParameter("idTicket");
		if(stridModi != null && !stridModi.trim().isEmpty()) {
			
		
		Integer idModi = null;
		if(stridModi != null) {
			idModi = Integer.parseInt(stridModi);
		} else {
			request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, "No se ha añadido ningun ID de Ticket");
			request.getRequestDispatcher("tickets.jsp").forward(request, response);
		}
		
		String strfechaInicio = request.getParameter("fechaInicio");
		Date fechaInicio = null;
		if(strfechaInicio != null) {
			try {
				Date fechaini = new SimpleDateFormat("dd/MM/yyyy").parse(strfechaInicio);
				fechaInicio = new Date(fechaini.getTime());
			} catch(ParseException e) {
				
			}
		}
		
		String strfechaFin = request.getParameter("fechaFin");
		Date dateFin = null;
		if(strfechaFin != null) {
			try {
				Date fechafin = new SimpleDateFormat("dd/MM/yyyy").parse(strfechaFin);
				dateFin = new Date(fechafin.getTime());
			} catch(ParseException e) {
				
			}
		}
		
		String descripcion = request.getParameter("descripcion");
		String estado = request.getParameter("estado");
		String idCliente = request.getParameter("idCliente");
		if(idCliente != null) {
			cliente = clienteDao.findById(idCliente);	
		}
		String dnigestor = request.getParameter("dniGestor");
		String[] gestores = dnigestor.replaceAll(" ", "").split(",");
		for(int i = 0; i < gestores.length ; i++) {
			gestor = gestorDao.findById(gestores[i]);
			if(gestor != null)
				equipoTrabajo.add(gestor);
		}
		if(cliente != null) {
			ticket = new Ticket(idModi, fechaInicio, dateFin, descripcion, estado, cliente, equipoTrabajo);
			ok = ticketDao.update(ticket);
		} else {
			ticket = new Ticket(idModi, fechaInicio, dateFin, descripcion, estado, equipoTrabajo);
			ok = ticketDao.update(ticket);
		}
		
		if(ok) {
			request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, "El Ticket se ha modificado");
			request.getRequestDispatcher("tickets.jsp").forward(request, response);
		} else{
			request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, "El Ticket No se ha modificado");
			request.getRequestDispatcher("tickets.jsp").forward(request, response);
		}
		}else {
			request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, "Completa los campos obligatorios");
			request.getRequestDispatcher("tickets.jsp").forward(request, response);
		}
	}
}
