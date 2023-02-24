package rzk;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Event;
import model.EventType;
import model.User;

/**
 * Session Bean implementation class PlannerBean
 */
@Stateful
@LocalBean
public class PlanerBean implements PlanerBeanRemote {

	User user;
	int userId;
	
	@PersistenceContext
	EntityManager em;
	
	@EJB
	EventTypeBean eventTypeBean;
	
    public PlanerBean() {
        // TODO Auto-generated constructor stub
    }
    
	@PostConstruct
    private void writeTimeAfter(){
    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	Calendar cal = Calendar.getInstance();
    	System.out.println("PlannerBean created:"+dateFormat.format(cal.getTime()));
    }
	
    @PreDestroy
    private void writeTimeBefore(){
    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	Calendar cal = Calendar.getInstance();
    	System.out.println("PlannerBean destroyed:"+dateFormat.format(cal.getTime()));
    }

	@Override
	public String login(String email, String password) {
		Query q = em.createQuery("SELECT u FROM User u WHERE u.email like :userEmail and u.password like :pass");
		q.setParameter("userEmail", email);
		q.setParameter("pass", password);
		
		List<User> users = q.getResultList();
		
		try {
			user = users.get(0);
			userId = users.get(0).getId();
			
			System.err.println("User: "+user.getEmail());
			System.err.println("User id: "+userId);
			return users.get(0).getEmail();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public List<Event> getEvents(Date d1) {
		System.out.println("Id trenutnog usera je: "+user.getId());
		Calendar calendar = Calendar.getInstance();
	    calendar.setTime(d1);
	    calendar.add(Calendar.DATE, +1);
	    Date tomorrow = calendar.getTime();
		Query q = em.createQuery("SELECT e FROM Event e WHERE e.fromDate >= :d1 AND e.fromDate < :d2 AND e.user =:idU");
		q.setParameter("d1", d1);
		q.setParameter("d2", tomorrow);
		q.setParameter("idU", user);
		
		
		List<Event> events = q.getResultList();
		
		if ( events.size() > 0) {
			return events;
		}
		System.out.println("Nije nasao sa ovim userom eventove.");
		return null;
	}

	@Override
	public boolean addEvent(String description, String eventType, Date fromDate, Date toDate) {
		// kad kreiras event imas i usera sacuvanog u promenljivoj user
		Event et = new Event();
		et.setDescription(description);
		et.setFromDate(fromDate);
		et.setToDate(toDate);
		
		System.out.println("Event type: "+eventType);		
		Query eventTyp = em.createQuery("SELECT e FROM EventType e WHERE e.name like :eType");
		eventTyp.setParameter("eType", eventType);
		EventType ev = (EventType) eventTyp.getSingleResult();
		
		et.setEventType(ev);
		et.setUser(user);
		
		try {
			em.persist(et);
			System.out.println("Uspesno smo dodali obavezu.");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public EventTypeBean getSingleton() {
		return eventTypeBean;
	}

	@Override
	public void logOut() {
		System.err.println("User: "+user.getEmail());
		System.err.println("User id: "+userId);
		user = null;
		userId = -1;
	}
	

	
}
