package web;

import java.io.IOException;

import javax.ejb.EJB;
import javax.jms.JMSException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.PorukaBean;

/**
 * Servlet implementation class CestitkaServlet
 */
@WebServlet("/CestitkaServlet")
public class CestitkaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	PorukaBean pb;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CestitkaServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String salje = request.getParameter("cardFrom");
		String primaoc = request.getParameter("cardTo");
		String email = request.getParameter("email");
		String tip = request.getParameter("tip");
		pb.setEmail(email);
		pb.setPrimaoc(primaoc);
		pb.setSalje(salje);
		pb.setType(tip);
		try {
			pb.sendQueueMess();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
