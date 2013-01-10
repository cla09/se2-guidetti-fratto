package swimv2SessionBean;

import javax.ejb.Stateless;
import javax.persistence.*;

import org.jboss.ejb3.annotation.RemoteBinding;

/**
 * Session Bean implementation class GestorePropostaAbilita
 */
@Stateless
@RemoteBinding(jndiBinding = "GestorePropostaAbilitaJNDI")
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
