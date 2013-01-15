package session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.ejb3.annotation.RemoteBinding;
import entity.Admin;

/**
 * Session Bean implementation class GestoreAdmin
 */
@Stateless
@RemoteBinding(jndiBinding = "GestoreAdminJNDI")
public class GestoreAdmin implements GestoreAdminRemote {

	@PersistenceContext(unitName = "swimv2_unit")
	private EntityManager gestoreDB;
	
    /**
     * Default constructor. 
     */
    public GestoreAdmin() {
    	super();
    }

	@Override
	public Admin getAdmin() {
		Admin adminDaRecuperare = gestoreDB.find(Admin.class, "admin");
		return adminDaRecuperare;
	}

	@Override
	public boolean modificaInformazioniAdmin(Admin adminModificato) {
		Admin adminDaAggiornare = getAdmin();
		adminDaAggiornare.setPassword(adminModificato.getPassword());
		adminDaAggiornare.setEmail(adminModificato.getEmail());
		adminDaAggiornare.setNome(adminModificato.getNome());
		adminDaAggiornare.setCognome(adminModificato.getCognome());
		adminDaAggiornare.setAvatar(adminModificato.getAvatar());
		try{
			gestoreDB.merge(adminDaAggiornare);
			return true;
		}
		catch(IllegalArgumentException e){
			System.out.println("errore nell'aggiornamento delle informazioni");
			return false;
		}
	}

}
