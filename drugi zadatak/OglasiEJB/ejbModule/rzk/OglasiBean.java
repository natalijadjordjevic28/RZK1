package rzk;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptors;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Ogla;
import model.OglasKorisnik;
import model.OglasPrijava;

/**
 * Session Bean implementation class OglasiBean
 */
@Stateful
@LocalBean
public class OglasiBean implements OglasiBeanRemote {
	
	int idUser;
	OglasKorisnik user;
	
	@PersistenceContext
	EntityManager em;

    /**
     * Default constructor. 
     */
    public OglasiBean() {
        // TODO Auto-generated constructor stub
    }
    
    @AroundInvoke
    public Object presretac(InvocationContext ctx) throws Exception {
    	System.out.println("Intercepted: "+ctx.getMethod().getName());
    	
    	
    	return ctx.proceed();
    }
    
    @Override
    public boolean login(String username, String password) {
    	
    	Query q = em.createQuery("SELECT u FROM OglasKorisnik u WHERE u.username LIKE :username AND u.password LIKE :pass");
    	q.setParameter("username", username);
    	q.setParameter("pass", password);
    	
    	List<OglasKorisnik> lista = q.getResultList();
    	
    	System.out.println(lista.size());
    	
    	try {
    		idUser = lista.get(0).getIdKorisnik();
    		user=lista.get(0);
    		System.out.println(lista.get(0).getNickname());
    		return true;
    		
    	} catch (Exception e) {
			return false;
		}
    }

	@Override
	@Interceptors({PreglediInterceptor.class})
	public List<Ogla> pretraziOglase(String name) {
		
		List<Ogla> lista = em.createQuery("SELECT o FROM Ogla o WHERE o.text LIKE :text")
				.setParameter("text", name).getResultList();
		
		return lista;
	}
	
	@Override
	public boolean kreirajOglas(String text) {
		if (text != null && !text.trim().isEmpty()) {
			try {
				Ogla oglas = new Ogla();
				oglas.setOglasKorisnik(user);
				oglas.setText(text);
				em.persist(oglas);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	@Override
	@Interceptors(value = JavljanjeNaOglasInterceptor.class)
	public boolean javljanjeNaOglas(int id, String text) {
		if (text != null && !text.trim().isEmpty()) {
			OglasPrijava javljanjeNaOglas = new OglasPrijava();
			javljanjeNaOglas.setOglasKorisnik(user);
			Ogla oglas = em.find(Ogla.class, id);
			javljanjeNaOglas.setOgla(oglas);
			javljanjeNaOglas.setText(text);
			em.persist(javljanjeNaOglas);
			return true;
		}
		return false;
	}
    
   
    

}
