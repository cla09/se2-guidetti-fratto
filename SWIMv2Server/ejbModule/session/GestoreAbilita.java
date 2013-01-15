package session;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.jboss.ejb3.annotation.RemoteBinding;
import entity.Abilita;

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
    	super();
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

	@Override
	public boolean creaAbilita(String nome, String descrizione, String pathIcona) {
		Abilita nuovaAbilita = new Abilita();
		nuovaAbilita.setNome(nome);
		nuovaAbilita.setDescrizioneAbilita(descrizione);
		nuovaAbilita.setIcona(pathIcona);
		try{
			gestoreDB.persist(nuovaAbilita);
			System.out.println("abilità inserita correttamente");
			return true;
		}
		catch(Exception e){
			System.out.println("errore nell'inserimento nel DB");
			return false;
		}
	}

}
