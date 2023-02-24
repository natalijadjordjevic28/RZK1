package rzk.web;

import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rzk.PlanerBeanRemote;

/**
 * Servlet implementation class AddEventServlet
 */
@WebServlet("/AddEventServlet")
public class AddEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEventServlet() {
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
		PlanerBeanRemote plannerBean = (PlanerBeanRemote) request.getSession().getAttribute("bean");
		request.setCharacterEncoding("UTF-8");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String description = request.getParameter("description");
		String fDate = request.getParameter("inputFromDate");
		String tDate = request.getParameter("inputToDate");
		String nameEventType = request.getParameter("nameEventType");
		
	
		System.out.println("F date: "+fDate);
		System.out.println("T date: "+tDate);
		
	
		Date fromDate = null;
		Date toDate = null;
		try {
			fromDate = formatter.parse(fDate.replace("T", " "));
			toDate = formatter.parse(tDate.replace("T", " "));
			System.out.println("From date in date:" +fromDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if (!plannerBean.addEvent(description, nameEventType, fromDate, toDate)) {
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		else 
			request.getRequestDispatcher("filter-events.jsp").forward(request, response);
		
	}

}
