package es.iespuertodelacruz.jmrs.incidencias.servlets.gestores;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.iespuertodelacruz.jmrs.incidencias.models.daos.GestorDAO;
import es.iespuertodelacruz.jmrs.incidencias.models.entities.Gestor;
import es.iespuertodelacruz.jmrs.incidencias.models.utils.GestorConexionDDBB;
import es.iespuertodelacruz.jmrs.incidencias.models.utils.Globals;

/**
 * Servlet implementation class EliminarGestor
 */
public class EliminarGestor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarGestor() {
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
		GestorDAO gestorDao = new GestorDAO(gc);
		Gestor gestor = null;
		ArrayList<Gestor> gestorLista = new ArrayList<Gestor>();
		ObjectMapper mapper = new ObjectMapper();
		
		String idgestorElim = request.getParameter("gestorId");
		if(idgestorElim != null && !idgestorElim.trim().isEmpty()) {
			boolean estado = gestorDao.delete(idgestorElim);
			
			if(estado) {
				request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, "El Gestor con id: " + idgestorElim + " se ha eliminado con exito");
				try {
					gestorLista = gestorDao.selectAll();
					String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(gestorLista);
					request.getSession().setAttribute(Globals.JSON, "Todos los clientes:\n" + json);
				} catch(JsonProcessingException e) {
					request.getSession().setAttribute(Globals.JSON, "Error al generar el Json");
				}
			} else {
				request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, "El Gestor con id: " + idgestorElim + "  NO se ha eliminado con exito");
			}
		} else {
			request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, "Debes de agregar un DNI");
		}
		
		request.getRequestDispatcher("gestores.jsp").forward(request, response);
	}
}
