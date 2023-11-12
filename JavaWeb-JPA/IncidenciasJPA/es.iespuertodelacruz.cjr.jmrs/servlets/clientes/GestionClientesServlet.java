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

import org.mindrot.jbcrypt.BCrypt;

import com.fasterxml.jackson.databind.ObjectMapper;

import entities.Cliente;
import entities.Role;
import entities.Usuario;
import repositories.ClienteRepository;
import repositories.UsuarioRepository;
import utils.Globals;

/**
 * Servlet implementation class GestionClientesServlet
 */
@WebServlet({ "/GestionClientesServlet", "/gestionclientes" })
public class GestionClientesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionClientesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();		
		Usuario user = (Usuario) session.getAttribute("user");
		
		// CREAR ENTITYMANAGERFACTORY
		EntityManagerFactory emf = (EntityManagerFactory) request.getServletContext().getAttribute("emf");
		ClienteRepository clienteR = new ClienteRepository(emf);
		UsuarioRepository usuarioR = new UsuarioRepository(emf);
		ArrayList<Cliente> clienteLista = new ArrayList<Cliente>();
		ObjectMapper mapper = new ObjectMapper();
				Usuario usuarioNuevo = null;
				Cliente cliente = null;
				Role rol = null;
		String opciones = request.getParameter("opciones");
		
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
		switch(opciones) {
			
		case "Crear cliente":
			//parametros de CLiente
			idCliente = request.getParameter("idCliente");
			nombre = request.getParameter("nombre");
			direccion = request.getParameter("direccion");
			telefono = request.getParameter("telefono");
			//parametros de Usuario
			nick = request.getParameter("nick");
			pass = request.getParameter("password");
			passHash = BCrypt.hashpw(pass, BCrypt.gensalt(6));
			email = request.getParameter("email");
			
			if(idCliente != null && !idCliente.trim().isEmpty() && 
					nombre != null && !nombre.trim().isEmpty() &&
					direccion != null && !direccion.trim().isEmpty() &&
					nick != null && !nick.trim().isEmpty() &&
					pass != null && !pass.trim().isEmpty() &&
					email != null && !email.trim().isEmpty()){
				
				rol.setIdRol(1);
				usuarioNuevo.setRole(rol);
				usuarioNuevo.setNick(nick);
				usuarioNuevo.setPassword(passHash);
				usuarioNuevo.setEmail(email);
				cliente.setIdCliente(idCliente);
				cliente.setNombre(nombre);
				cliente.setTelefono(telefono);
				cliente.setDireccion(direccion);
				cliente.setUsuario(usuarioNuevo);
				
				Usuario createUsuario = usuarioR.save(usuarioNuevo);
				Cliente createCliente = clienteR.save(cliente);
				
				if(createCliente != null) {
					request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, "se ha completado"); 
					
					/*try {
						clienteLista = clienteR.selectAll();
						String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(clienteLista);
						request.getSession().setAttribute(Globals.JSON, "Todos los clientes:\n" + json);
					} catch(JsonProcessingException e) {
						request.getSession().setAttribute(Globals.JSON, "Error al generar el Json");
					}*/
				}
				else {
					request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, "NO se ha completado"); 
				}
			}
			else {
				request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, "Debes de agregar los datos obligatorios"); 
			}
			
			if(user.getRole().getIdRol()==1) {
				request.getRequestDispatcher("dashboard.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("user_login.jsp").forward(request, response);
			}
			break;
		case "Eliminar cliente":
			idCliente = request.getParameter("idCliente");
			nick = request.getParameter("nick");
			if(idCliente != null && nick != null) {
				ok = usuarioR.delete(nick);
				if(ok) {
					boolean okElimCliente = clienteR.delete(idCliente);
					if(okElimCliente) {
						request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, "Se ha eliminado el cliente correctamente");
						request.getRequestDispatcher("dashboard.jsp").forward(request, response);
					}
				}
			} else {
				request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, "Debes de agregar los datos obligatorios"); 
			}
			break;
		case "Modificar cliente":
			//request.getSession().getAttribute(idCliente);
			idCliente = request.getParameter("idCliente");
			if(idCliente != null && !idCliente.trim().isEmpty()) {
				clienteR.findById(idCliente);
				Cliente clienteModi = clienteR.findById(idCliente);
				if(clienteModi != null) {
					request.setAttribute("nombre", clienteModi.getNombre());
					request.setAttribute("direccion", clienteModi.getDireccion());
					request.setAttribute("telefono", clienteModi.getTelefono());
					request.setAttribute("nick", clienteModi.getUsuario().getNick());
					request.setAttribute("email", clienteModi.getUsuario().getEmail());
					//request.setAttribute("password", clienteModi.getUsuario().getPassword());
					//FALTA CONTINUAR ESTA PARTE
				}else {
					request.getSession().setAttribute(Globals.MENSAJE_MOSTRAR, "No has introducido un id de Cliente correcto");
				}
			}
			break;
		case "Buscar cliente":
			idCliente = request.getParameter("idCliente");
			if(idCliente != null && !idCliente.trim().isEmpty()) {
				Cliente clienteBuscar = clienteR.findById(idCliente);
				//falta mostrar en json
			}
			
			break;
		}
	}

}
