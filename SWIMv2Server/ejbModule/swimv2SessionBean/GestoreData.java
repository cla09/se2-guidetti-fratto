package swimv2SessionBean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.ejb3.annotation.RemoteBinding;

/**
 * Session Bean implementation class GestoreData
 */
@Stateless
@RemoteBinding(jndiBinding = "GestoreDataJNDI")
public class GestoreData implements GestoreDataRemote {
	
	@PersistenceContext(unitName = "swimv2_unit")
	private EntityManager gestoreDB;

    /**
     * Default constructor. 
     */
    public GestoreData() {
        // TODO Auto-generated constructor stub
    }

}
