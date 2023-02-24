package rzk;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import model.Event;

@Remote
public interface PlanerBeanRemote {

	public String login(String email, String password);
	
	public List<Event> getEvents(Date d1);
	
	public boolean addEvent(String description, String eventType, Date fromDate, Date toDate);
	
	public EventTypeBean getSingleton();
	
	public void logOut();
}
