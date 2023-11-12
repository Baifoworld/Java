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
 * Servlet implementation class EliminarCliente
 */
public class EliminarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarCliente() {
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
		ArrayList<Cliente> clienteLista = new ArrayList<Cliente>();
		ObjectMapper mapper = new ObjectMapper();
		
		String idclienteElim = request.getParameter("clienteId");
		if(idclienteElim != null && !idclienteElim.trim().isEmpty()) {
			
			
			boolean estado = clienteDao.delete(idclienteElim);
				
				if(estado) {
					request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, "El cliente con id: " + idclienteElim + " se ha eliminado con exito");
					try {
						clienteLista = clienteDao.selectAll();
						String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(clienteLista);
						request.getSession().setAttribute(Globals.JSON, "Todos los clientes:\n" + json);
					} catch(JsonProcessingException e) {
						request.getSession().setAttribute(Globals.JSON, "Error al generar el Json");
					}
				}
				else {
					request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, "El cliente con id: " + idclienteElim + " NO se ha podido eliminar");
				}
		}
			request.getRequestDispatcher("clientes.jsp").forward(request, response);
		}
}
