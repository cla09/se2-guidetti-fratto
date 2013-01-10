package swimv2SessionBean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.ejb3.annotation.RemoteBinding;

/**
 * Session Bean implementation class GestorePropostaAbilita
 */
@Stateless
@RemoteBinding(jndiBinding = "GestorPropostaeAbilitaJNDI")
public class GestorePropostaAbilita implements GestorePropostaAbilitaRemote {

	@PersistenceContext(unitName = "swimv2_unit")
	private EntityManager gestoreDB;
	
    /**
     * Default constructor. 
     */
    public GestorePropostaAbilita() {
        // TODO Auto-generated constructor stub
    }

}
