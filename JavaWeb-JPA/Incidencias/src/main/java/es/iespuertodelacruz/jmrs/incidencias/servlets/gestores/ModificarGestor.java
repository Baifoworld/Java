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
 * Servlet implementation class ModificarGestor
 */
public class ModificarGestor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificarGestor() {
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
		
		String dnigestorModi = request.getParameter("gestorId");
		String nombreModi = request.getParameter("gestorNombre") ;
		String apellidoModi = request.getParameter("gestorApellidos") ;
		if(dnigestorModi != null && !dnigestorModi.trim().isEmpty()) {
			gestor = new Gestor(dnigestorModi, nombreModi, apellidoModi);
			boolean ok = gestorDao.update(gestor);
			
			if(ok) {
				request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, "se ha modificado correctamente"); 
				try {
					gestorLista = gestorDao.selectAll();
					String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(gestorLista);
					request.getSession().setAttribute(Globals.JSON, "Todos los clientes:\n" + json);
				} catch(JsonProcessingException e) {
					request.getSession().setAttribute(Globals.JSON, "Error al generar el Json");
				}
			}else		
				request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, "Ha habido un error al modificar el gestor con DNI: " + dnigestorModi); 
			request.getRequestDispatcher("gestores.jsp").forward(request, response);

		} else {
			request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, "Debes de completar el campo obligatorio");
			request.getRequestDispatcher("gestores.jsp").forward(request, response);
		}
		
	}

}
