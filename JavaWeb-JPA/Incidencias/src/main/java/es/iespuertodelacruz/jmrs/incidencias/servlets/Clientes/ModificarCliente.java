package es.iespuertodelacruz.jmrs.incidencias.servlets.Clientes;

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

import es.iespuertodelacruz.jmrs.incidencias.models.daos.ClienteDAO;
import es.iespuertodelacruz.jmrs.incidencias.models.entities.Cliente;
import es.iespuertodelacruz.jmrs.incidencias.models.utils.GestorConexionDDBB;
import es.iespuertodelacruz.jmrs.incidencias.models.utils.Globals;

/**
 * Servlet implementation class ModificarCliente
 */
public class ModificarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificarCliente() {
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
		ClienteDAO clienteDao = new ClienteDAO(gc);
		Cliente cliente = null;
		ArrayList<Cliente> clienteLista = new ArrayList<Cliente>();
		ObjectMapper mapper = new ObjectMapper();
		
		String idClientemodi = request.getParameter("clienteId");
		String nombremodi = request.getParameter("clienteNombre");
		String direccionmodi = request.getParameter("clienteDireccion");
		String telefonomodi = request.getParameter("clienteTelefono");
		if(idClientemodi != null && !idClientemodi.trim().isEmpty()) {
			cliente = new Cliente(idClientemodi, nombremodi, direccionmodi,telefonomodi);
			boolean estado = clienteDao.update(cliente);
			
			if(estado) {
				request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, "se ha modificado correctamente"); 
				try {
					clienteLista = clienteDao.selectAll();
					String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(clienteLista);
					request.getSession().setAttribute(Globals.JSON, "Todos los clientes:\n" + json);
				} catch(JsonProcessingException e) {
					request.getSession().setAttribute(Globals.JSON, "Error al generar el Json");
				}
			} else {
				request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, "NO se ha modificado correctamente"); 
			}
		} else {
			request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, "Debes de rellenar todos los campos"); 

		}
		
		
		request.getRequestDispatcher("clientes.jsp").forward(request, response);
	}

}
