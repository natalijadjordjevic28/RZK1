package rzk;

import javax.ejb.Local;

import model.EventType;

import java.util.List;

@Local
public interface EventTypeBeanLocal {

	public List<EventType> getEventsType();
}

