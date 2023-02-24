package rzk;

import java.util.HashMap;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Ogla;

@Singleton
@Startup
public class Statistika implements StatistikaLocal {

	private HashMap<Integer, Integer> mapViews;
	private int respondToAdCounter;

	@PersistenceContext
	private EntityManager em;

	@Resource
	private TimerService timerService;

	@SuppressWarnings("unused")
	private Timer timer;

	public Statistika() {
		mapViews = new HashMap<Integer, Integer>();
	}

	public HashMap<Integer, Integer> getMapViews() {
		return mapViews;
	}

	public void setMapViews(HashMap<Integer, Integer> mapViews) {
		this.mapViews = mapViews;
	}

	@Override
	public void increaseRespondToAdCounter() {
		respondToAdCounter = respondToAdCounter + 1;
	}

	@Override
	public void updateMapViews(Ogla ad) {
		if (mapViews.containsKey(ad.getIdOglas())) {
			mapViews.put(ad.getIdOglas(), mapViews.get(ad.getIdOglas()) + 1);
		} else {
			mapViews.put(ad.getIdOglas(), 1);
		}
	}

	@PostConstruct
	public void startTimer() {
		TimerConfig timerConfig = new TimerConfig();
		timerConfig.setPersistent(false);
		timer = timerService.createIntervalTimer(0, 15 * 60 * 1000, timerConfig);
	}

	@Schedule(persistent = false)
	private void printCounter() {
		System.out.println(this.getClass().getSimpleName() + ": Respond to ad counter at the end of the day is "
				+ respondToAdCounter);
		respondToAdCounter = 0;
	}

	@Timeout
	private void updateDatabase() {
		for (Entry<Integer, Integer> entry : mapViews.entrySet()) {
			Ogla oglas = em.find(Ogla.class, entry.getKey());
			oglas.setBrojPregleda(oglas.getBrojPregleda() + entry.getValue());
			em.merge(oglas);
		}
		mapViews.clear();
		System.out.println(this.getClass().getSimpleName() + ": Database updated");
	}
}
