package rzk;

import java.util.List;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import model.Ogla;

public class PreglediInterceptor {

	@EJB
	StatistikaLocal statistika;
	
	
	@AroundInvoke
	public Object presretniPreglede(InvocationContext ctx) throws Exception {

		List<Ogla> oglasi = (List<Ogla>) ctx.getMethod().invoke(ctx.getTarget(), ctx.getParameters());
		System.out.println(oglasi);

		for (Ogla o : oglasi) {
			statistika.updateMapViews(o);
		}
		return ctx.proceed();
	}
}
