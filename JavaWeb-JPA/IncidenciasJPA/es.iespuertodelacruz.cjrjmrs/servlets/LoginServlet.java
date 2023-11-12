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

import entities.Usuario;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet({"/Login", "/login"})
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Usuario user = (Usuario)session.getAttribute("user");
		String redirect = "login.jsp";
		if(user != null)
			redirect="users/monedas.jsp";
		else {
			String paramUser = request.getParameter("user");
			String paramPassword = request.getParameter("password");
			EntityManagerFactory emf =(EntityManagerFactory)request.getServletContext().getAttribute("emf");
			UsuarioRepository usuarioR = new UsuarioRepository(emf);
			
			Usuario usuario = usuarioR.findByName(paramUser);
			
			if(usuario != null) {
				
				boolean okLogin = BCrypt.checkpw(paramPassword,usuario.getPassword());
				/*
				 * Para una página de registro. Se usaría la siguiente sentencia:
				 * 	String enHash = BCrypt.hashpw( paramPassword, BCrypt.gensalt(10));
				 * Observar que el 10 es el salt por defecto si se quiere se puede
				 * establecer otro. Lo importante es que todas las password-hash
				 * sean establecidas iguales 
				*/
				if( okLogin) {
					request.getSession().setAttribute("user", usuario);
					redirect="user_login.jsp";
				}
				
			}
		}
		response.sendRedirect(redirect);
	}

}
