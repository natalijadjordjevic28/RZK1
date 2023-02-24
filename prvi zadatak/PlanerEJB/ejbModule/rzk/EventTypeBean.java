package rzk;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.EventType;

/**
 * Session Bean implementation class EventTypeBean
 */
@Singleton
@LocalBean
public class EventTypeBean implements EventTypeBeanLocal {

	@PersistenceContext
	EntityManager em;
    /**
     * Default constructor. 
     */
	
	@PostConstruct
    private void writeTimeAfter(){
    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	Calendar cal = Calendar.getInstance();
    	System.out.println("EventTypeBean created:"+dateFormat.format(cal.getTime()));
    }
	
    @PreDestroy
    private void writeTimeBefore(){
    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	Calendar cal = Calendar.getInstance();
    	System.out.println("EventTypeBean destroyed:"+dateFormat.format(cal.getTime()));
    }
    public EventTypeBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<EventType> getEventsType() {
		Query all = em.createQuery("SELECT e FROM EventType e");
		
		List<EventType> et = all.getResultList();
		
		if ( et.size() > 0) {
			return et;
		} 
		return null;
	}

}