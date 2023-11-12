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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import entities.Cliente;
import entities.Role;
import entities.Usuario;
import repositories.ClienteRepository;
import repositories.UsuarioRepository;
import utils.Globals;

/**
 * Servlet implementation class AgregarCliente
 */
@WebServlet({ "/AgregarCliente", "/agregarcliente" })
public class AgregarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgregarCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
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
				
				//parametros de CLiente
				String idCliente = request.getParameter("idCliente");
				String nombre = request.getParameter("nombre");
				String direccion = request.getParameter("direccion");
				String telefono = request.getParameter("telefono");
				//parametros de Usuario
				String nick = request.getParameter("nick");
				String pass = request.getParameter("password");
				String passHash = BCrypt.hashpw(pass, BCrypt.gensalt(6));
				String email = request.getParameter("email");
				
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
				
	}

}
