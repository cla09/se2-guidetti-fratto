package session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.ejb3.annotation.RemoteBinding;

import entity.Profilo;
import entity.User;

/**
 * Session Bean implementation class GestoreProfilo
 */
@Stateless
@RemoteBinding(jndiBinding = "GestoreProfiloJNDI")
public class GestoreProfilo implements GestoreProfiloRemote {

	@PersistenceContext(unitName = "swimv2_unit")
	private EntityManager gestoreDB;
	
    /**
     * Default constructor. 
     */
    public GestoreProfilo() {
        // TODO Auto-generated constructor stub
    }
    
    /*
	@Override
	public String registra(String nickname, String password, String email, String nome, String cognome, String percorsoAvatar, 
							String citta, String sesso, int annoNascita) {
		
		User nuovoUser = new User();
		
		
		
	}

	@Override
	public Profilo recuperaDatiProfilo(String nickname) {
		// TODO Auto-generated method stub
		return null;
	}
	*/
}
