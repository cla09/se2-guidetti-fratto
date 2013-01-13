package session;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.ejb3.annotation.RemoteBinding;

import entity.Abilita;
import entity.User;

/**
 * Session Bean implementation class GestoreAbilita
 */
@Stateless
@RemoteBinding(jndiBinding = "GestoreAbilitaJNDI")
public class GestoreAbilita implements GestoreAbilitaRemote {

	@PersistenceContext(unitName = "swimv2_unit")
	private EntityManager gestoreDB;
	
    /**
     * Default constructor. 
     */
    public GestoreAbilita() {
        // TODO Auto-generated constructor stub
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<Abilita> recuperaAbilitaSistema() {
		
		List<Abilita> abilitaDisponibili;
		
		Query q = gestoreDB.createQuery("SELECT a FROM Abilita a");
		
		try{
			abilitaDisponibili = (List<Abilita>) q.getResultList();;
			
			return abilitaDisponibili;
		}
		catch (NoResultException e) {
			return null;
		}
	}

	
}
