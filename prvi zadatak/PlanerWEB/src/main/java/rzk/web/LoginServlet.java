package rzk.web;

import java.io.IOException;

import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.EventType;
import rzk.PlanerBean;
import rzk.PlanerBeanRemote;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static Context initialContext;
	
	private static final String PKG_INTERFACES = "org.jboss.ejb.client.naming";
	
	private static Context getInitialContext() throws NamingException {
		if (initialContext == null) {
			Properties prop = new Properties();
			prop.put(Context.URL_PKG_PREFIXES, PKG_INTERFACES);
			initialContext = new InitialContext(prop);
		}
		return initialContext;
	}
	
	
	private static String getLookUpName() {
		final String appName = "PlanerEAR"; 
		final String moduleName = "PlanerEJB"; 
		final String distinctName = ""; 
		final String beanName = PlanerBean.class.getSimpleName();; 
		final String interfaceName = PlanerBeanRemote.class.getName(); 
		String name = "ejb:"+appName+"/"+moduleName+"/"+distinctName+"/"+beanName+"!"+interfaceName+ "?stateful"; 
		return name;
	}
	
	private static PlanerBeanRemote lookUp() {
		PlanerBeanRemote bean = null;
		
		try {
			Context context = getInitialContext();
	
			String lookUpName = getLookUpName();
			bean = (PlanerBeanRemote) context.lookup(lookUpName);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bean;
	}
    public LoginServlet() {
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
		if (plannerBean == null) {
			plannerBean = lookUp();
		}
		String username = request.getParameter("email");
		String password = request.getParameter("password");
		
		String email = plannerBean.login(username, password);
		
		List<EventType> events = plannerBean.getSingleton().getEventsType();
		
		if ( events.size() > 0) {
			System.err.println("Size of events: "+events.size());
			request.getSession().setAttribute("eventList", events);
		}
		if (email == "") {
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} else {
			request.getSession().setAttribute("email", username);
			request.getSession().setAttribute("bean", plannerBean);
			request.getRequestDispatcher("home.jsp").forward(request, response);
		}
	}

}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


