package swimv2SessionBean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.ejb3.annotation.RemoteBinding;

import swimv2EntityBean.Abilita;
import exception.DBException;

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

	
}
