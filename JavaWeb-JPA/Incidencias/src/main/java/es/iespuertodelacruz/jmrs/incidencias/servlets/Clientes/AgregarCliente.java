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
 * Servlet implementation class AgregarCliente
 */
public class AgregarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgregarCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = request.getServletContext();
		HttpSession session = request.getSession();
		GestorConexionDDBB gc = new GestorConexionDDBB("incidencias", "root", "1q2w3e4r");
		ClienteDAO clienteDao = new ClienteDAO(gc);
		ArrayList<Cliente> clienteLista = new ArrayList<Cliente>();
		ObjectMapper mapper = new ObjectMapper();
				
				Cliente cliente = null;
				
				String idCliente = request.getParameter("clienteId");
				String nombre = request.getParameter("clienteNombre");
				String direccion = request.getParameter("clienteDireccion");
				String telefono = request.getParameter("clienteTelefono");
				
				if(idCliente != null && !idCliente.trim().isEmpty() && 
						nombre != null && !nombre.trim().isEmpty() &&
						direccion != null && !direccion.trim().isEmpty()) {
					
					cliente = new Cliente(idCliente,nombre,direccion,telefono);
					
					Cliente create = clienteDao.save(cliente);
					if(create != null) {
						request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, "se ha completado"); 
						try {
							clienteLista = clienteDao.selectAll();
							String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(clienteLista);
							request.getSession().setAttribute(Globals.JSON, "Todos los clientes:\n" + json);
						} catch(JsonProcessingException e) {
							request.getSession().setAttribute(Globals.JSON, "Error al generar el Json");
						}
					}
					else {
						request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, "NO se ha completado"); 
					}
				}
				else {
					request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, "Debes de agregar los datos obligatorios"); 
				}
				
				request.getRequestDispatcher("clientes.jsp").forward(request, response);
	}

}
