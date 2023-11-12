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
import es.iespuertodelacruz.jmrs.incidencias.models.entities.Cliente;
import es.iespuertodelacruz.jmrs.incidencias.models.entities.Gestor;
import es.iespuertodelacruz.jmrs.incidencias.models.utils.GestorConexionDDBB;
import es.iespuertodelacruz.jmrs.incidencias.models.utils.Globals;

/**
 * Servlet implementation class AgregarGestor
 */
public class AgregarGestor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgregarGestor() {
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
		
		String dniGestor = request.getParameter("gestorId");
		String nombreGestor = request.getParameter("gestorNombre");
		String apellidos = request.getParameter("gestorApellidos");
		if(dniGestor != null && !dniGestor.trim().isEmpty() && nombreGestor != null && !nombreGestor.trim().isEmpty()) {
			gestor = new Gestor(dniGestor, nombreGestor, apellidos);
			Gestor create = gestorDao.save(gestor);
			if(create != null) {
				request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, "se ha completado");
				try {
					gestorLista = gestorDao.selectAll();
					String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(gestorLista);
					request.getSession().setAttribute(Globals.JSON, "Todos los clientes:\n" + json);
				} catch(JsonProcessingException e) {
					request.getSession().setAttribute(Globals.JSON, "Error al generar el Json");
				}
			} else {
				request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, "NO se ha podido crear el Gestor");
			}
			request.getRequestDispatcher("gestores.jsp").forward(request, response);
		} else {
			request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, "Debes de completar los campos obligatorios");
			request.getRequestDispatcher("gestores.jsp").forward(request, response);
		}
		
		
		
	}

}
