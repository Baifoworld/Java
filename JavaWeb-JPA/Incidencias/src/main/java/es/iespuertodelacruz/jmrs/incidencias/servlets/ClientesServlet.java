package es.iespuertodelacruz.jmrs.incidencias.servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.iespuertodelacruz.jmrs.incidencias.models.daos.ClienteDAO;
import es.iespuertodelacruz.jmrs.incidencias.models.entities.Cliente;
import es.iespuertodelacruz.jmrs.incidencias.models.utils.GestorConexionDDBB;
import es.iespuertodelacruz.jmrs.incidencias.models.utils.Globals;

/**
 * Servlet implementation class CrearClientes
 */
@WebServlet({ "/Clientes", "/clientes" })
public class ClientesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("clientes.jsp").forward(request, response);
		
		ServletContext context = request.getServletContext();
		HttpSession session = request.getSession();
		session.setAttribute(Globals.MENSAJE_MOSTRAR, "");
		
		request.getServletContext().getAttribute("clientes");
	}
/*
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	/*
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		ServletContext context = request.getServletContext();
		//HttpSession session = request.getSession();
		GestorConexionDDBB gc = new GestorConexionDDBB("incidencias", "root", "1q2w3e4r");
		ClienteDAO clienteDao = new ClienteDAO(gc);
		
		String submit = request.getParameter("submit");
		
		switch(submit) {
			case "Crear":
				
				Cliente cliente = null;
				
				String idCliente = request.getParameter("clienteId");
				String nombre = request.getParameter("clienteNombre");
				String direccion = request.getParameter("clienteDireccion");
				String telefono = request.getParameter("clienteTelefono");
				
				if(idCliente != null && nombre != null && direccion != null) {
					cliente = new Cliente(idCliente,nombre,direccion,telefono);
					//ClienteDAO clienteDao = new ClienteDAO(gc);
					System.out.println(idCliente + " " + nombre);
					Cliente create = clienteDao.save(cliente);
					if(create != null) {
			request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, "se ha completado"); 
		}
		else {
			request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, "NO se ha completado"); 
		}
				}
				//CrearCliente(request, response, clienteDao);
				break;
			case "Modificar":
				ModificarCliente(request, response, clienteDao);
				break;
			case "Eliminar":
				EliminarCliente(request, response, clienteDao);
				break;
			case "Mostrar":
				MostrarCliente(request, response, clienteDao);
				break;
		}
		doGet(request, response);
	}
	
	private void CrearCliente(HttpServletRequest request, HttpServletResponse response, ClienteDAO clienteDao) throws ServletException, IOException {
		
		Cliente cliente = null;
		
		String idCliente = request.getParameter("clienteId");
		String nombre = request.getParameter("clienteNombre");
		String direccion = request.getParameter("clienteDireccion");
		String telefono = request.getParameter("clienteTelefono");
		cliente = new Cliente(idCliente,nombre,direccion,telefono);
		Cliente create = clienteDao.save(cliente);
		if(create != null) {
			request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, "se ha completado"); 
		}
		else {
			request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, "NO se ha completado"); 
		}
		/*if(idCliente != null && nombre != null && direccion != null) {
			cliente = new Cliente(idCliente,nombre,direccion,telefono);
			//ClienteDAO clienteDao = new ClienteDAO(gc);
			if(cliente != null) {
				Cliente create = clienteDao.save(cliente);
				if(create != null) {
					
						request.getRequestDispatcher("dashboard.jsp").forward(request, response);
				}		
			}
			
		}	
			
	}
	
	private void ModificarCliente(HttpServletRequest request, HttpServletResponse response, ClienteDAO clienteDao) {
		
	}
	
	private void EliminarCliente(HttpServletRequest request, HttpServletResponse response, ClienteDAO clienteDao) {
		
	}
	
	private void MostrarCliente(HttpServletRequest request, HttpServletResponse response, ClienteDAO clienteDao) {
		
	}
	/*
		ServletContext context = request.getServletContext();
		HttpSession session = request.getSession();
		gc = new GestorConexionDDBB("incidencias", "root", "1q2w3e4r");
		
		String idCliente = request.getParameter("clienteId");
		String nombre = request.getParameter("clienteNombre");
		String direccion = request.getParameter("clienteDireccion");
		String telefono = request.getParameter("clienteTelefono");
		
		if(idCliente != null && nombre != null && direccion != null && telefono != null) {
			Cliente cliente = new Cliente(idCliente,nombre,direccion,telefono);
			ClienteDAO clienteDao = new ClienteDAO(gc);
			Cliente create = clienteDao.save(cliente);
			if(create != null) {
		
				ArrayList<Cliente> clientes = null;
				
				clientes = clienteDao.selectAll();
				if(clientes != null) {
					session.setAttribute(Globals.LISTA_CLIENTES, clientes);
				}				
				request.getRequestDispatcher("crearcliente.jsp").forward(request, response);
			}
		}
	}*/

}
