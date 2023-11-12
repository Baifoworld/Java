package es.iespuertodelacruz.jmrs.incidencias.servlets.Seguimineot;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.iespuertodelacruz.jmrs.incidencias.models.daos.SeguimientoDAO;
import es.iespuertodelacruz.jmrs.incidencias.models.entities.Seguimiento;
import es.iespuertodelacruz.jmrs.incidencias.models.entities.Ticket;
import es.iespuertodelacruz.jmrs.incidencias.models.utils.GestorConexionDDBB;
import es.iespuertodelacruz.jmrs.incidencias.models.utils.Globals;

/**
 * Servlet implementation class MostrarSeguimiento
 */
public class MostrarSeguimiento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MostrarSeguimiento() {
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
		Seguimiento seguimiento = null;
		ArrayList<Seguimiento> seguimientosLista = new ArrayList<Seguimiento>();
		ObjectMapper mapper = new ObjectMapper();
		
		String stridSeguimiento = request.getParameter("seguimientoId");
		
		
		Integer idSeguimiento = null;
		if(stridSeguimiento != null && !stridSeguimiento.trim().isEmpty()) {
			idSeguimiento = Integer.parseInt(stridSeguimiento);
		} else {
			request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, "Debes de añadir un ID del seguimiento");
			request.getRequestDispatcher("seguimientos.jsp").forward(request, response);
		}
		
		seguimiento = seguimientoDao.findById(idSeguimiento);
		if(seguimiento != null) {
			
			if(seguimiento.getFecha() != null) {
				request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, seguimiento.getIdSeguimiento()+ "\n"
						+ seguimiento.getNormalFecha() + "\n"
						+seguimiento.getComentario() + "\n"
						+seguimiento.getTicket().getIdTicket() + "\n"
						+seguimiento.getGestor().getDNI());
			} else {
				request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, seguimiento.getIdSeguimiento()+ "\n"
						+ "Fecha del seguimiento nula" + "\n"
						+seguimiento.getComentario() + "\n"
						+seguimiento.getTicket().getIdTicket() + "\n"
						+seguimiento.getGestor().getDNI());
			}
			
			
			
			seguimientosLista = seguimientoDao.selectAll();
			if(seguimientosLista.size()>0) {
				
				String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(seguimientosLista);
				request.getSession().setAttribute(Globals.JSON, "Todos los Seguimientos:\n" + json);
			} 
			 
			request.getRequestDispatcher("seguimientos.jsp").forward(request, response);
		} else {
			request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, "No has introducido un ID correcto (no existe el Seguimiento");
			request.getRequestDispatcher("seguimientos.jsp").forward(request, response);
		}
	}

}
