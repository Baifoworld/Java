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
import es.iespuertodelacruz.jmrs.incidencias.models.entities.Ticket;
import es.iespuertodelacruz.jmrs.incidencias.models.utils.GestorConexionDDBB;
import es.iespuertodelacruz.jmrs.incidencias.models.utils.Globals;

/**
 * Servlet implementation class MostrarCliente
 */
public class MostrarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MostrarCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
		
		String idclienteMostrar= request.getParameter("clienteId");
		if(idclienteMostrar != null && !idclienteMostrar.trim().isEmpty()) {
cliente = clienteDao.findById(idclienteMostrar);
			
			if(cliente != null) {
				String idCliente = cliente.getIdCliente();
				String nombre = cliente.getNombreContacto();
				String direccion = cliente.getDireccion();
				String telefono = cliente.getTelefono();
				request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, idCliente + ", " 
						+ nombre + ", "
						+ direccion + ", " 
						+ telefono);
				

				try {
					clienteLista = clienteDao.selectAll();
					String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(clienteLista);
					request.getSession().setAttribute(Globals.JSON, "Todos los clientes:\n" + json);
				} catch(JsonProcessingException e) {
					request.getSession().setAttribute(Globals.JSON, "Error al generar el Json");
				}
			}
			else {
				request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, "El cliente con id: " + idclienteMostrar + " NO se ha podido encontrar");
			}
		} else {
			request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, "Debes de rellenar el campo obligatorio");

		}
		
		
			request.getRequestDispatcher("clientes.jsp").forward(request, response);
	}

}
