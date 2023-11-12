package es.iespuertodelacruz.jmrs.incidencias.servlets.Seguimineot;

import java.io.IOException;
import java.util.ArrayList;
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
import es.iespuertodelacruz.jmrs.incidencias.models.entities.Seguimiento;
import es.iespuertodelacruz.jmrs.incidencias.models.entities.Ticket;
import es.iespuertodelacruz.jmrs.incidencias.models.utils.GestorConexionDDBB;
import es.iespuertodelacruz.jmrs.incidencias.models.utils.Globals;

/**
 * Servlet implementation class EliminarSeguimiento
 */
public class EliminarSeguimiento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarSeguimiento() {
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
		Ticket ticket;
		ArrayList<Seguimiento> seguimientosLista = new ArrayList<Seguimiento>();
		ObjectMapper mapper = new ObjectMapper();
		
		String stridSegui = request.getParameter("seguimientoId");
		if(stridSegui != null && !stridSegui.trim().isEmpty()) {
			
		
		Integer idseguiElim = null;
		try {
			idseguiElim = Integer.parseInt(stridSegui);
		}catch (Exception e) {
			
		}
		boolean estado = seguimientoDao.delete(idseguiElim);
			
		if(estado) {
			request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, "El seguimiento " + idseguiElim + " se ha eliminado correctamente");
			seguimientosLista = seguimientoDao.selectAll();
			if(seguimientosLista.size()>0) {
				
				String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(seguimientosLista);
				request.getSession().setAttribute(Globals.JSON, "Todos los Seguimientos:\n" + json);
			} 
			request.getRequestDispatcher("seguimientos.jsp").forward(request, response);
		} else {
			request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, "El seguimiento " + idseguiElim + " No se ha eliminado correctamente");
			request.getRequestDispatcher("seguimientos.jsp").forward(request, response);
		}
		}else {
			request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, "Debes de completar los campos obligatorios");

			request.getRequestDispatcher("seguimientos.jsp").forward(request, response);
		}
	}

}
