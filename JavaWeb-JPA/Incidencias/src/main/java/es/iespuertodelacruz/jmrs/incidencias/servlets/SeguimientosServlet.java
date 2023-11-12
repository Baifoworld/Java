package es.iespuertodelacruz.jmrs.incidencias.servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.iespuertodelacruz.jmrs.incidencias.models.daos.SeguimientoDAO;
import es.iespuertodelacruz.jmrs.incidencias.models.utils.GestorConexionDDBB;
import es.iespuertodelacruz.jmrs.incidencias.models.utils.Globals;

/**
 * Servlet implementation class Seguimientos
 */
@WebServlet({ "/Seguimientos", "/seguimientos" })
public class SeguimientosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SeguimientosServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("seguimientos.jsp").forward(request, response);
		
		ServletContext context = request.getServletContext();
		HttpSession session = request.getSession();
		session.setAttribute(Globals.MENSAJE_MOSTRAR, "");
		
		request.getServletContext().getAttribute("seguimientos");
	}

}
