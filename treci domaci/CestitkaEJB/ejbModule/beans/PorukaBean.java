package beans;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;

import model.Card;

/**
 * Session Bean implementation class PorukaBean
 */
@Singleton
@LocalBean
public class PorukaBean {

	@Inject
	JMSContext context;

	@Resource(mappedName = "java:/jms/queue/Cest")
	private Destination cards;

	private String type;
	private String salje;
	private String primaoc;
	private String email;

	public void sendQueueMess() throws JMSException {
		ObjectMessage objmess = context.createObjectMessage();
		Card card = new Card();
		card.setCardFrom(salje);
		card.setCardTo(primaoc);
		card.setEmail(email);
		card.setType(type);

		objmess.setObject(card);

		context.createProducer().send(cards, objmess);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSalje() {
		return salje;
	}

	public void setSalje(String salje) {
		this.salje = salje;
	}

	public String getPrimaoc() {
		return primaoc;
	}

	public void setPrimaoc(String primaoc) {
		this.primaoc = primaoc;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Default constructor.
	 */
	public PorukaBean() {
		// TODO Auto-generated constructor stub
	}

}
