package rzk;

import javax.ejb.Remote;

@Remote
public interface RegisterBeanRemote {

	public boolean register(String ime, String prezime, String username, String password);
}
