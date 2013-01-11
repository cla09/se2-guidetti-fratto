package session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.ejb3.annotation.RemoteBinding;

/**
 * Session Bean implementation class GestoreFeedback
 */
@Stateless
@RemoteBinding(jndiBinding = "GesotreFeedbackJNDI")
public class GestoreFeedback implements GestoreFeedbackRemote {
	
	@PersistenceContext(unitName = "swimv2_unit")
	private EntityManager gestoreDB;

    /**
     * Default constructor. 
     */
    public GestoreFeedback() {
        // TODO Auto-generated constructor stub
    }

}
