package session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.ejb3.annotation.RemoteBinding;
import entity.User;

/**
 * Session Bean implementation class GestoreUser
 */
@Stateless
@RemoteBinding(jndiBinding = "GestoreUserJNDI")
public class GestoreUser implements GestoreUserRemote {

	@PersistenceContext(unitName = "swimv2_unit")
	private EntityManager gestoreDB;
	
    /**
     * Default constructor. 
     */
    public GestoreUser() {
    	super();
    }

    @Override
	public boolean controllaDisponibilitaNickname(String nickname) {
		if(getUser(nickname) != null){
			//nickname non disponibile
			System.out.println("nickname non disponibile");
			return false;
		}
		//nickname disponibile
		System.out.println("nickname disponibile");
		return true;
	}
    
    
	@Override
	public boolean registra(String nickname, String password, String email, String nome, String cognome, 
									String percorsoAvatar, String citta, String sesso, int annoNascita) {
		User nuovoUser = new User();
		nuovoUser.setNickname(nickname);
		nuovoUser.setPassword(password);
		nuovoUser.setEmail(email);
		nuovoUser.setNome(nome);
		nuovoUser.setCognome(cognome);
		nuovoUser.setAvatar(percorsoAvatar);
		nuovoUser.setCitta(citta);
		nuovoUser.setSesso(sesso);
		nuovoUser.setAnnoNascita(annoNascita);
		try{
			gestoreDB.persist(nuovoUser);
			System.out.println("utente inserito correttamente");
			return true;
		}
		catch(IllegalArgumentException e){
			System.out.println("errore nell'inserimento nel DB");
			return false;
		}

	}

	@Override
	public User getUser(String nickname) {
		User userDaRecuperare = gestoreDB.find(User.class, nickname);
		return userDaRecuperare;
	}

	@Override
	public boolean modificaInformazioniUser(User userModificato) {
		User userDaAggiornare = getUser(userModificato.getNickname());
		userDaAggiornare.setPassword(userModificato.getPassword());
		userDaAggiornare.setEmail(userModificato.getEmail());
		userDaAggiornare.setNome(userModificato.getNome());
		userDaAggiornare.setCognome(userModificato.getCognome());
		userDaAggiornare.setAvatar(userModificato.getAvatar());
		userDaAggiornare.setCitta(userModificato.getCitta());
		userDaAggiornare.setSesso(userModificato.getSesso());
		userDaAggiornare.setAnnoNascita(userModificato.getAnnoNascita());
		try{
			gestoreDB.merge(userDaAggiornare);
			return true;
		}
		catch(IllegalArgumentException e){
			System.out.println("errore nell'aggiornamento delle informazioni");
			return false;
		}
	}

	@Override
	//DA TESTARE
	public boolean confermaCancellazioneUser(String nickname) {
		User userDaCancellare = getUser(nickname);
		try{
			gestoreDB.remove(userDaCancellare);
			System.out.println("user rimosso correttamente");
			return true;
		}
		catch(IllegalArgumentException e){
			System.out.println("errore nella cancellazione");
			return false;
		}
	}

	
    
}
