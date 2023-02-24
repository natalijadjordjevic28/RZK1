package rzk.web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Event;
import rzk.PlanerBeanRemote;

/**
 * Servlet implementation class EventsServlet
 */
@WebServlet("/EventsServlet")
public class EventsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EventsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PlanerBeanRemote plannerBean = (PlanerBeanRemote) request.getSession().getAttribute("bean");
		request.getSession().removeAttribute("allEvents");
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		String fDate = request.getParameter("fromDate");
		System.out.println("From date:"+fDate);
		Date fromDate = null;
		try {
			fromDate = formatter.parse(fDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		List<Event> allEvents = plannerBean.getEvents(fromDate);
		
		if (allEvents != null) {
			System.out.println("Size of desirable events: "+allEvents.size());
			request.getSession().setAttribute("allEvents", allEvents);
		}

		request.getRequestDispatcher("filter-events.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
