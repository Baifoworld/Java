package servlets.clientes;

import java.io.IOException;
import java.util.ArrayList;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import entities.Cliente;
import entities.Role;
import entities.Usuario;
import repositories.ClienteRepository;
import repositories.UsuarioRepository;
import utils.Globals;

/**
 * Servlet implementation class ModificarCliente
 */
@WebServlet({ "/clientes/vercliente","/clientes/Vercliente" })
public class VerCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();		
		Usuario user = (Usuario) session.getAttribute("cliente");
		Cliente cliente = (Cliente) session.getAttribute("cliente");
		// CREAR ENTITYMANAGERFACTORY
		EntityManagerFactory emf = (EntityManagerFactory) request.getServletContext().getAttribute("emf");
		ClienteRepository clienteR = new ClienteRepository(emf);
		UsuarioRepository usuarioR = new UsuarioRepository(emf);
		Usuario usuarioNuevo = null;
		Cliente clienteNuevo = null;
		Role rol = null;
		
		//parametros de CLiente
		String idCliente;
		String nombre;
		String direccion;
		String telefono;
		//parametros de Usuario
		String nick;
		String pass;
		String passHash;
		String email;
		boolean ok = false;
		String opciones = request.getParameter("opciones");
		
		if(cliente != null) {
			request.setAttribute("cliente",cliente);
				//request.setAttribute("password", clienteModi.getUsuario().getPassword());
				//FALTA CONTINUAR ESTA PARTE
		}else {
			request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, "Ha habido un error al crear el cliente");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
		
		// CREAR ENTITYMANAGERFACTORY
		EntityManagerFactory emf = (EntityManagerFactory) request.getServletContext().getAttribute("emf");
		ClienteRepository clienteR = new ClienteRepository(emf);
		UsuarioRepository usuarioR = new UsuarioRepository(emf);
		Usuario usuarioNuevo = null;
		Cliente clienteNuevo = null;
		Role rol = null;
		
		String opciones = request.getParameter("opciones");
		switch(opciones) {
			case "Modificar cliente":
				/*clienteNuevo.setNombre( request.getParameter("nombre"));
				clienteNuevo.setDireccion(request.getParameter("direccion"));
				clienteNuevo.setTelefono(request.getParameter("telefono"));
				usuarioNuevo.setRole(user.UsuagetRole());
				usuarioNuevo.setPassword(user.getPassword());
				usuarioNuevo.setNick(request.getParameter("nick"));
				usuarioNuevo.setEmail(request.getParameter("email"));
				clienteNuevo.setUsuario(usuarioNuevo);
				//falta hacer save*/
				break;
			case "Eliminar cliente":
				
				if(cliente != null) {
					boolean ok = clienteR.delete(cliente.getIdCliente());
					if(ok) {
						boolean okElimUsuario = usuarioR.delete(cliente.getUsuario().getNick());
						if(okElimUsuario) {
							request.setAttribute(Globals.MENSAJE_MOSTRAR, "Se ha eliminado el cliente correctamente");
							request.getRequestDispatcher("../user_login.jsp").forward(request, response);
						}
					}
				} else {
					request.setAttribute(Globals.MENSAJE_MOSTRAR, "Debes de agregar los datos obligatorios"); 
				}
				break;
		}
	}
	
}
