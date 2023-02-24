package beans;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Card;

/**
 * Message-Driven Bean implementation class for: GenerateCardsMDB
 */
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destination", propertyValue = "java:/jms/queue/Cest"), @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue")
		}, 
		mappedName = "java:/jms/queue/Cest")
public class GenerateCardsMDB implements MessageListener {
	
	@PersistenceContext
	EntityManager em;
	@Resource(mappedName = "java:/jms/topic/Cest")
	private Destination generate;
	
	@Inject
	JMSContext context;

    /**
     * Default constructor. 
     */
    public GenerateCardsMDB() {
        // TODO Auto-generated constructor stub
    	
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
        try {
        	ObjectMessage mess=(ObjectMessage) message;
			Card c=(Card) mess.getObject();
			writeToDB(c);
			System.out.println("Cestitka procesirana");
			
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
    
    public void writeToDB(Card c) throws JMSException {
    	em.persist(c);
    	ObjectMessage mess = context.createObjectMessage();
    	mess.setObject(c);
		context.createProducer().send(generate, mess);

    	System.out.println("Upisano u bazu");
    }
    
    @PostConstruct
    public void created() {
    	System.out.println("MDB kreiran");
    }

}
