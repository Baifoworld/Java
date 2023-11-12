package es.iespuertodelacruz.jmrs.incidencias.servlets.Seguimineot;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
 * Servlet implementation class AgregarSeguimiento
 */
public class AgregarSeguimiento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgregarSeguimiento() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = request.getServletContext();
		HttpSession session = request.getSession();
		GestorConexionDDBB gc = new GestorConexionDDBB("incidencias", "root", "1q2w3e4r");
		SeguimientoDAO seguimientoDao = new SeguimientoDAO(gc);
		GestorDAO gestorDao = new GestorDAO(gc);
		TicketDAO ticketDao = new TicketDAO(gc);
		Seguimiento seguimiento = null;
		Gestor gestor = null;
		Ticket ticket = null;
		ArrayList<Seguimiento> seguimientosLista = new ArrayList<Seguimiento>();
		ObjectMapper mapper = new ObjectMapper();
		Seguimiento create = null;
		
		String stridTicket = request.getParameter("idTicket");
		Integer idTicket;
		try {
			idTicket = Integer.parseInt(stridTicket);
			ticket = ticketDao.findById(idTicket);
		} catch(Exception e){
			
		}
		
		String strfecha = request.getParameter("seguimientoFecha");
		Date fecha = null;
		if(strfecha != null) {
			try {
				Date cambiarfecha = new SimpleDateFormat("dd/MM/yyyy").parse(strfecha);
				fecha = new Date(cambiarfecha.getTime());
			} catch(ParseException e) {
				
			}
		} else {
			request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, "Debes de añadir el campo de Fecha");
			request.getRequestDispatcher("seguimientos.jsp").forward(request, response);
		}
		
		String comentario = request.getParameter("seguimientoComentario");
		String dniGestor = request.getParameter("seguimientodni");
		if(dniGestor != null && !dniGestor.trim().isEmpty()) {
			String[] cortedniGestor = dniGestor.replaceAll(" ", "").split(",");
			
			gestor = gestorDao.findById(dniGestor);
			if(comentario != null && !comentario.trim().isEmpty() && ticket != null) {
				for(int i = 0; i < cortedniGestor.length ; i++) {
					gestor = gestorDao.findById(cortedniGestor[i]);
					seguimiento = new Seguimiento(fecha, comentario, gestor, ticket);
					create = seguimientoDao.save(seguimiento);
				}
				
				if(create != null) {
					request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, "El seguimiento se ha creado correctamente");
					seguimientosLista = seguimientoDao.selectAll();
					if(seguimientosLista.size()>0) {
						
						String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(seguimientosLista);
						request.getSession().setAttribute(Globals.JSON, "Todos los Seguimientos:\n" + json);
					} 
					request.getRequestDispatcher("seguimientos.jsp").forward(request, response);
				} else {
					request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, "El seguimiento NO se ha creado correctamente");
					request.getRequestDispatcher("seguimientos.jsp").forward(request, response);
				}
			} else {
				request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, "Debes de añadir todos los campos obligatorios");
				request.getRequestDispatcher("seguimientos.jsp").forward(request, response);
			}
			
		} else {
			request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, "Debes de añadir el campo de DNI del Gestor");
			request.getRequestDispatcher("seguimientos.jsp").forward(request, response);
		}		
		
	}

}
