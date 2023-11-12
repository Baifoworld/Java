package servlets;

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
import entities.Gestore;
import entities.Role;
import entities.Usuario;
import repositories.GestorRepository;
import repositories.UsuarioRepository;

/**
 * Servlet implementation class MenuGestorServlet
 */
@WebServlet({ "/gestores/gestiongestores", "/gestores/Gestiongestores" })
public class GestionGestoresServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionGestoresServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String dni = request.getParameter("dni");
		String apellidos = request.getParameter("apellidos");
		String nick = request.getParameter("nick");
		String nombre = request.getParameter("nombre");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String opciones = request.getParameter("opciones");
		
		// CREAR ENTITYMANAGERFACTORY
		EntityManagerFactory emf = (EntityManagerFactory) request.getServletContext().getAttribute("emf");
		GestorRepository gestorR = new GestorRepository(emf);
		UsuarioRepository usuarioR = new UsuarioRepository(emf);
		switch(opciones) {
		case "Crear usuario":
			
			//CREAR USUARIO:
			Role rol = new Role();
			rol.setIdRol(1);
			rol.setNombreRol("administrador");
			
			Usuario usuario = new Usuario();
			String passwordHash = BCrypt.hashpw( password, BCrypt.gensalt(6));
			usuario.setRole(rol);
			usuario.setNick(nick);
			usuario.setPassword(passwordHash);
			usuario.setEmail(email);
			usuarioR.save(usuario);
			
			//CREAR GESTOR:
			Gestore gestor = new Gestore();
			gestor.setDni(dni); 
			gestor.setNombre(nombre); 
			gestor.setApellidos(apellidos);
			gestor.setUsuario(usuario);
			
			gestorR.save(gestor);
			if(gestor != null) {
				ArrayList<Gestore> gestores = new ArrayList<Gestore>();
				gestor = gestorR.findById(dni);
				gestores.add(gestor);
				
			
				ObjectMapper mapper = new ObjectMapper();
				String mostrarGestor = mapper
				.writerWithDefaultPrettyPrinter()
				.writeValueAsString(gestores.toString());
				
				request.setAttribute("gestores",gestores);
				request.getRequestDispatcher("mostrargestores.jsp").forward(request, response);
			}
			break;
		case "Buscar gestor":
			dni = request.getParameter("dni");
			
			if(dni != null) {
				emf = (EntityManagerFactory) request.getServletContext().getAttribute("emf");
				gestorR = new GestorRepository(emf);
				ArrayList<Gestore> gestores = new ArrayList<Gestore>();
				gestor = gestorR.findById(dni);
				
				if(gestor != null) {
					gestores.add(gestor);
					ObjectMapper mapper = new ObjectMapper();
					String mostrarGestor = mapper
					.writerWithDefaultPrettyPrinter()
					.writeValueAsString(gestores.toString());
					
					request.setAttribute("gestor",gestores);
					request.getRequestDispatcher("mostrargestores.jsp").forward(request, response);
				}
			}
			break;
		case "Ver gestores":
			emf = (EntityManagerFactory) request.getServletContext().getAttribute("emf");
			gestorR = new GestorRepository(emf);
			ArrayList<Gestore> gestores = new ArrayList<Gestore>();
			gestores = (ArrayList<Gestore>) gestorR.findAll();
			
			if(gestores != null) {
				ObjectMapper mapper = new ObjectMapper();
				String mostrarGestor = mapper
				.writerWithDefaultPrettyPrinter()
				.writeValueAsString(gestores.toString());
				
				request.setAttribute("gestores",gestores);
				request.getRequestDispatcher("mostrargestores.jsp").forward(request, response);
			}
			break;
		case "Eliminar gestor":
			dni = request.getParameter("dni");
			nick = request.getParameter("nick");
			if(dni != null && nick != null) {
				if(gestorR.delete(dni)) {
					if(usuarioR.delete(nick)) {
						request.setAttribute("deletedTrueMessage", "Se ha eliminado el gestor correctamente");
						request.getRequestDispatcher("gestiongestores.jsp").forward(request, response);
					}
				}
			} else {
				request.setAttribute("deletedFalseMessage", "Debes de agregar los datos obligatorios");
				request.getRequestDispatcher("gestiongestores.jsp").forward(request, response);
			}
			
			break;
			
		case "Editar gestor":
			dni = request.getParameter("dni");
			apellidos = request.getParameter("apellidos");
			nick = request.getParameter("nick");
			nombre = request.getParameter("nombre");
			password = request.getParameter("password");
			email = request.getParameter("email");
			
			emf = (EntityManagerFactory) request.getServletContext().getAttribute("emf");
			gestorR = new GestorRepository(emf);
			usuarioR = new UsuarioRepository(emf);
			
			rol = new Role();
			rol.setIdRol(1);
			rol.setNombreRol("administrador");
			
			//Update de usuario:
			usuario = new Usuario();
			passwordHash = BCrypt.hashpw(password, BCrypt.gensalt(6));
			usuario.setRole(rol);
			usuario.setNick(nick);
			usuario.setPassword(passwordHash);
			usuario.setEmail(email);
			
			break;
		}
	}

}
