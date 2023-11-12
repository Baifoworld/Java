package es.iespuertodelacruz.jmrs.incidencias.servlets.Seguimineot;

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

import com.fasterxml.jackson.databind.ObjectMapper;

import es.iespuertodelacruz.jmrs.incidencias.models.daos.GestorDAO;
import es.iespuertodelacruz.jmrs.incidencias.models.daos.SeguimientoDAO;
import es.iespuertodelacruz.jmrs.incidencias.models.daos.TicketDAO;
import es.iespuertodelacruz.jmrs.incidencias.models.entities.Gestor;
import es.iespuertodelacruz.jmrs.incidencias.models.entities.Seguimiento;
import es.iespuertodelacruz.jmrs.incidencias.models.entities.Ticket;
import es.iespuertodelacruz.jmrs.incidencias.models.utils.GestorConexionDDBB;
import es.iespuertodelacruz.jmrs.incidencias.models.utils.Globals;

/**
 * Servlet implementation class ModificarSeguimiento
 */
public class ModificarSeguimiento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificarSeguimiento() {
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
		SeguimientoDAO seguimientoDao = new SeguimientoDAO(gc);
		GestorDAO gestorDao = new GestorDAO(gc);
		TicketDAO ticketDao = new TicketDAO(gc);
		Seguimiento seguimiento;
		Gestor gestor;
		Ticket ticket = null;
		ArrayList<Seguimiento> seguimientosLista = new ArrayList<Seguimiento>();
		ObjectMapper mapper = new ObjectMapper();
		
		String stridModi = request.getParameter("seguimientoID");
		Integer idModi = null;
		if(stridModi != null && !stridModi.trim().isEmpty()) {
			try {
				idModi = Integer.parseInt(stridModi);
			} catch(Exception e) {
				
			}	
		} else {
			request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, "Debes de añadir el ID del seguimiento");
			request.getRequestDispatcher("seguimientos.jsp").forward(request, response);
		}
		
		String strfechaModi = request.getParameter("fechaseguimiento");
		Date fechaModi = null;
		if(strfechaModi != null) {
			try {
				Date cambiarfecha = new SimpleDateFormat("dd/MM/yyyy").parse(strfechaModi);
				fechaModi = new Date(cambiarfecha.getTime());
			} catch(ParseException e) {
				
			}
		}
		String comentario = request.getParameter("comentarioSeguimiento");
		String stridTicket = request.getParameter("idTicket");
		Integer idTicketMOdi = null;
		try {
			idTicketMOdi = Integer.parseInt(stridTicket);
		} catch (Exception e) {
			
		}
		ticket = ticketDao.findById(idTicketMOdi);
		String dniGestorModi = request.getParameter("dniGestor");
		gestor = gestorDao.findById(dniGestorModi);
		seguimiento = new Seguimiento(idModi, fechaModi, comentario, gestor, ticket);
		boolean ok = seguimientoDao.update(seguimiento);
		if(ok) {
			request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, "El seguimiento se ha modificado correctamente");
			
			seguimientosLista = seguimientoDao.selectAll();
			if(seguimientosLista.size()>0) {
				
				String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(seguimientosLista);
				request.getSession().setAttribute(Globals.JSON, "Todos los Seguimientos:\n" + json);
			} 
			 
			request.getRequestDispatcher("seguimientos.jsp").forward(request, response);
		} else{
			request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, "El seguimiento No se ha modificado correctamente");
			request.getRequestDispatcher("seguimientos.jsp").forward(request, response);
		}
	}

}
