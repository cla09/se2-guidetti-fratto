package session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.ejb3.annotation.RemoteBinding;

import entity.Admin;
import entity.User;

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
        // TODO Auto-generated constructor stub
    }

	@Override
	public Admin getAdmin() {
		
		System.out.println("sono nel server");
		Admin adminDaRecuperare;

		Query q = gestoreDB.createQuery("SELECT a FROM Admin a WHERE a.nickname = :nickname");
		q.setParameter("nickname", "admin");

		try{
			adminDaRecuperare = (Admin) q.getSingleResult();
			return adminDaRecuperare;
		}
		catch (NoResultException e) {
			System.out.println("errore nel recupero");
			return null;
		}
	}

	@Override
	public boolean modificaInformazioniAdmin(Admin adminModificato) {
		Admin adminDaAggiornare;
		
		adminDaAggiornare = getAdmin();
		
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
