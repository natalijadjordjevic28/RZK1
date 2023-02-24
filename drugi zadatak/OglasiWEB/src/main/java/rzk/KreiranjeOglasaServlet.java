package rzk;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class KreiranjeOglasaServlet
 */
@WebServlet("/KreiranjeOglasaServlet")
public class KreiranjeOglasaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KreiranjeOglasaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		OglasiBeanRemote bean = (OglasiBeanRemote) request.getSession().getAttribute("bean");
		String text = request.getParameter("text");
		boolean ok = bean.kreirajOglas(text);
		if (ok) {
			request.setAttribute("poruka", "Oglas je uspesno kreiran!");
			request.getRequestDispatcher("home.jsp").forward(request, response);
		} else {
			request.setAttribute("poruka", "Greska prilikom kreiranja oglasa!");
			request.getRequestDispatcher("kreirajOglas.jsp").forward(request, response);
		}
	}

}
