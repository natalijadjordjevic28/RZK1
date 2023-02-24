package rzk;

import javax.ejb.Local;

import model.Ogla;

@Local
public interface StatistikaLocal {
	
	void updateMapViews(Ogla ad);

	void increaseRespondToAdCounter();

}
