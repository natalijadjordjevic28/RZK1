package beans;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import model.Card;

/**
 * Message-Driven Bean implementation class for: GraduateCardsMDB
 */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/topic/Cest"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic") }, mappedName = "java:/jms/topic/Cest")
public class GraduateCardsMDB implements MessageListener {

	/**
	 * Default constructor.
	 */
	public GraduateCardsMDB() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see MessageListener#onMessage(Message)
	 */
	public void onMessage(Message message) {

		try {
			ObjectMessage mess = (ObjectMessage) message;
			Card c = (Card) mess.getObject();

			System.out.println("Cestitka za maturu");
			System.out.println("Salje: " + c.getCardFrom());
			System.out.println("Prima: " + c.getCardTo());
			System.out.println("---------");


		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}
}
