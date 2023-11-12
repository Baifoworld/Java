package servlets;

import java.io.IOException;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import entities.Gestore;
import repositories.GestorRepository;

/**
 * Servlet implementation class EditarGestorServlet
 */
@WebServlet({ "/gestores/Editargestor", "/gestores/editargestor" })
public class EditarGestorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditarGestorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dni = request.getParameter("dni");
		EntityManagerFactory emf = (EntityManagerFactory) request.getServletContext().getAttribute("emf");
		GestorRepository gestorR = new GestorRepository(emf);
		
		Gestore gestor = gestorR.findById(dni);
		if(gestor != null) {
			request.setAttribute("gestor",gestor);
			request.getRequestDispatcher("editargestor.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("gestiongestores.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
