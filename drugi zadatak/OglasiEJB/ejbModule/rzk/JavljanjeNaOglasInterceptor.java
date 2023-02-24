package rzk;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class JavljanjeNaOglasInterceptor {
	
	@EJB
	private StatistikaLocal statistika;

	@AroundInvoke
	public Object interceptRespondToAdMethod(InvocationContext ctx) throws Exception {
		statistika.increaseRespondToAdCounter();
		return ctx.proceed();
	}

}
