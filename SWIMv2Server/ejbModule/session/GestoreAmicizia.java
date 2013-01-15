package session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.ejb3.annotation.RemoteBinding;

/**
 * Session Bean implementation class GestoreAmicizia
 */
@Stateless
@RemoteBinding(jndiBinding = "GestoreAmiciziaJNDI")
public class GestoreAmicizia implements GestoreAmiciziaRemote {

	@PersistenceContext(unitName = "swimv2_unit")
	private EntityManager gestoreDB;
	
	private GestoreDataLocal gestoreData;
    /**
     * Default constructor. 
     */
    public GestoreAmicizia() {
    	super();
    }

	@Override
	public void inserisciRichiestaAmicizia(String nicknameRichiedente,	String nicknameDestinatario) {
		
		
	}

}
