package session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
        // TODO Auto-generated constructor stub
    }

    @Override
	public boolean controllaDisponibilitaNickname(String nickname) {
				
		if(getUser(nickname) != null){
			//nickname non disponibile
			System.out.println("nickname non disponibile");
			return false;
		}
		else{
			//nickname disponibile
			System.out.println("nickname disponibile");
			return true;
		}
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
			System.out.println("utente inserito correttamente");
			gestoreDB.persist(nuovoUser);
			return true;
		}
		catch(Exception e){
			System.out.println("errore nell'inserimento nel DB");
			return false;
		}

	}

	@Override
	public User getUser(String nickname) {
		User userDaRecuperare;

		Query q = gestoreDB.createQuery("SELECT u FROM User u WHERE u.nickname = :nickname");
		q.setParameter("nickname", nickname);

		try{
			userDaRecuperare = (User) q.getSingleResult();
			return userDaRecuperare;
		}
		catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public boolean modificaInformazioniUser(User userModificato) {
		User userDaAggiornare;
		
		userDaAggiornare = getUser(userModificato.getNickname());
		
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
		
		User userDaCancellare;
		
		userDaCancellare = getUser(nickname);
		
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