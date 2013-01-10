package swimv2SessionBean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.ejb3.annotation.RemoteBinding;

/**
 * Session Bean implementation class GestoreAiuto
 */
@Stateless
@RemoteBinding(jndiBinding = "GestoreAiutoJNDI")
public class GestoreAiuto implements GestoreAiutoRemote {

	@PersistenceContext(unitName = "swimv2_unit")
	private EntityManager gestoreDB;
    
	
	/**
     * Default constructor. 
     */
    public GestoreAiuto() {
        // TODO Auto-generated constructor stub
    }

}
