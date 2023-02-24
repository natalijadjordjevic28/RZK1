package rzk.webService;

import javax.ejb.Remote;
import javax.jws.WebService;

@Remote
@WebService
public interface CountryInfRemote {
	
	String getCode(String s);

}
