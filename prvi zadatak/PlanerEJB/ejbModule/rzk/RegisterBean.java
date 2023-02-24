package rzk;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.User;

/**
 * Session Bean implementation class RegisterBean
 */
@Stateless
@LocalBean
public class RegisterBean implements RegisterBeanRemote {

	User user;
	int userId;
	
	@PersistenceContext
	EntityManager em;
	
    public RegisterBean() {
        // TODO Auto-generated constructor stub
    }
    
	@PostConstruct
    private void writeTimeAfter(){
    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	Calendar cal = Calendar.getInstance();
    	System.out.println("RegisterBean created:"+dateFormat.format(cal.getTime()));
    }
	
    @PreDestroy
    private void writeTimeBefore(){
    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	Calendar cal = Calendar.getInstance();
    	System.out.println("RegisterBean destroyed:"+dateFormat.format(cal.getTime()));
    }

	@Override
	public boolean register(String ime, String prezime, String username, String password) {
		User u = new User();
		u.setEmail(username);
		u.setFirstName(ime);
		u.setLastName(prezime);
		u.setPassword(password);
		
		try {
			em.persist(u);
			System.out.println("Uspesno smo se registrovali.");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;		
	}

}
