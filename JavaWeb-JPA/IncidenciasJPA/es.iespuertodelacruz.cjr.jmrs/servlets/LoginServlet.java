package servlets;

import java.io.IOException;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import entities.Cliente;
import entities.Usuario;
import repositories.ClienteRepository;
import repositories.UsuarioRepository;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet({ "/Login", "/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nick = request.getParameter("nick");
		String password = request.getParameter("password");
		
		// CREAR ENTITYMANAGERFACTORY
		EntityManagerFactory emf = (EntityManagerFactory) request.getServletContext().getAttribute("emf");
		UsuarioRepository usuarioR = new UsuarioRepository(emf);
		
		
		//BUSCAS USUARIO CON EL REPOSITORIO USANDO EL METODO FINDBYID
		Usuario usuario = usuarioR.findByName(nick);
	
		// COMPROBAR QUE EL USUARIO EXISTE
		if (usuario != null) {
			
			// CHECK QUE LA CONTRAÑESA INTRODUCIDA POR EL USUARIO COINCIDE CON LA CONTRA DE LA BD.
			boolean okLogin = BCrypt.checkpw(password, usuario.getPassword());
			System.out.println(usuario.getPassword());
			/*
			 * Para una página de registro. Se usaría la si
			 * guiente sentencia: String enHash
			 * = BCrypt.hashpw( paramPassword, BCrypt.gensalt(6)); Observar que el 10 es el
			 * salt por defecto si se quiere se puede establecer otro. Lo importante es que
			 * todas las password-hash sean establecidas iguales
			 */
			// COMPRUEBA SI ES TRUE EL RESULTADO DE LA CONTRASEÑA Y REDIRIGE AL MENU CLIENTES O GESTORES ADEMAS DE INICIAR LA SESION DEL USUARIO.
			System.out.println(usuario.getRole().getIdRol());
			if (okLogin) {
				if (usuario.getRole().getIdRol() == 1) {
					request.getSession().setAttribute("administrador", usuario);
					request.getRequestDispatcher("gestores/dashboard.jsp").forward(request, response);
				} else if (usuario.getRole().getIdRol() == 2) {
					request.getSession().setAttribute("cliente", usuario);
					ClienteRepository clienteR = new ClienteRepository(emf);
					Cliente cliente = clienteR.findByNick(nick);
					request.getSession().setAttribute("cliente",cliente);
					request.getRequestDispatcher("clientes/menu.jsp").forward(request, response);
				}
				

			} 
		} else {
			request.setAttribute("errorMessage", "El usuario introducido no existe");
			request.getRequestDispatcher("user_login.jsp").forward(request, response);
		}
	}
}
