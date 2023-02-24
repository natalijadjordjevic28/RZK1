package rzk.web;

import java.io.IOException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rzk.RegisterBean;
import rzk.RegisterBeanRemote;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
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
		final String beanName = RegisterBean.class.getSimpleName();
		; // bean name
		final String interfaceName = RegisterBeanRemote.class.getName(); 
		String name = "ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + interfaceName;
		return name;
	}

	private static RegisterBeanRemote lookUp() {
		RegisterBeanRemote bean = null;

		try {
			Context context = getInitialContext();
			String lookUpName = getLookUpName();
			bean = (RegisterBeanRemote) context.lookup(lookUpName);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bean;
	}

	public RegisterServlet() {
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
		RegisterBeanRemote plannerBean = null;
		if (plannerBean == null) {
			plannerBean = lookUp();
		}
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String username = request.getParameter("email");
		String password = request.getParameter("password");
		System.out.println(username);
		System.out.println(lastname);
		System.out.println(firstname);
		System.out.println(password);

		if (plannerBean.register(firstname, lastname, username, password)) {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}

	}

}
