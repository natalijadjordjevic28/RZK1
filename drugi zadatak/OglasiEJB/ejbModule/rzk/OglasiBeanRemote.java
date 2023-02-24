package rzk;

import java.util.List;

import javax.ejb.Remote;

import model.Ogla;

@Remote
public interface OglasiBeanRemote {
	
	public boolean login(String username, String password);
	
	public List<Ogla> pretraziOglase(String name);

	public boolean kreirajOglas(String text);

	public boolean javljanjeNaOglas(int id, String text);

}
