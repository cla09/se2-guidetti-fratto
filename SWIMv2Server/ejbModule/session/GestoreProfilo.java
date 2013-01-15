package session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.ejb3.annotation.RemoteBinding;

import entity.Profilo;

/**
 * Session Bean implementation class GestoreProfilo
 * 
 */
@Stateless
@RemoteBinding(jndiBinding = "GestoreProfiloJNDI")
public class GestoreProfilo implements GestoreProfiloRemote {

	@PersistenceContext(unitName = "swimv2_unit")
	private EntityManager gestoreDB;
	
	
    public GestoreProfilo() {
    	super();
    }


	@Override
	public String getRuolo(String nickname) {
		String ruoloDaRecuperare;
		if(nickname.equals("admin")){
			ruoloDaRecuperare = "admin";
		} else {
				ruoloDaRecuperare = "user";
		}
		return ruoloDaRecuperare;
	}

	@Override
	public boolean controllaCredenziali(String nickname, String password) {
		Profilo profiloDaRecuperare =  gestoreDB.find(Profilo.class, nickname);
		if(profiloDaRecuperare == null) {
			return false;
		}
		if(!profiloDaRecuperare.getPassword().equals(password)) {
			return false;
		}
		return true;
	}


	
}
