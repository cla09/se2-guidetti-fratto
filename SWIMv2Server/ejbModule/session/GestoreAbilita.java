package session;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.ejb3.annotation.RemoteBinding;

import entity.Abilita;
import entity.Dichiarazione;
import entity.User;

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

	@SuppressWarnings("unchecked")
	@Override
	public boolean setAbilitaDichiarate(String nickname, List<Integer> idAbilita) {
		//devo inserire in dichiarazione una riga per ogni indice ricevuto
		
		Dichiarazione nuovaDichiarazione;
		User user;
		Abilita abilita;
		Query q;
		int numeroFeedback;
		int mediaValutazioni;
		
		//recupero lo user
		user = getUserInDichiarazione(nickname);
		if(user == null){
			//nessuno user esistente con quel nickname
			return false;
		}

		for(Integer i: idAbilita){
			nuovaDichiarazione = new Dichiarazione();
			
			//recupero l'abilita
			q = gestoreDB.createQuery("SELECT a FROM Abilita a WHERE a.codiceAbilita = :codiceAbilita");
			q.setParameter("codiceAbilita", i);
			
			try{
				abilita = (Abilita) q.getSingleResult();
				System.out.println("abilita recuperata: " + abilita.getNome());
			}
			catch (NoResultException e) {
				abilita = null;
				System.out.println("non esiste nessuna abilità con quell'id");
				return false;
			}
			
			//determino il numero di feedback (se già presente in dichiarazione lascio il valore, altrimenti 0)	
			//determino la media delle valutazioni (se già presente in dichiarazione lascio il valore, altrimenti 0)
			
			//faccio prima una select(numFeedback, media) per recuperare l'eventuale dichiarazione con nick e abilita[i]
			//nella catch (dichiarazione non presente) assegno a zero il numeroFeedback e la media
			
			
			q = gestoreDB.createQuery("SELECT d.numeroFeedback, d.mediaValutazioni " +
					"					FROM Dichiarazione d " +
					"					WHERE d.userDichiarante = :user" +
					"					AND d.abilitaDichiarata = :abilita");
			q.setParameter("user", user);
			q.setParameter("abilita", abilita);
			
			try{
				List<Integer> risultati = (List<Integer>) q.getResultList();
				numeroFeedback = risultati.get(0);
				mediaValutazioni = risultati.get(1);
				
				System.out.println("valori recuperati... num: " + numeroFeedback + "  media: " + mediaValutazioni);
			}
			catch (NoResultException e) {
				numeroFeedback = 0;
				mediaValutazioni = 0;
				System.out.println("nuova dichiarazione");
			}
			
			
			nuovaDichiarazione.setUserDichiarante(user);
			nuovaDichiarazione.setAbilitaDichiarata(abilita);
			nuovaDichiarazione.setNumeroFeedback(numeroFeedback);
			nuovaDichiarazione.setMediaValutazioni(mediaValutazioni);
			
			//inserisco la nuova dichiarazione
			
			try{
				gestoreDB.persist(nuovaDichiarazione);
				System.out.println("dichiarazione abilità inserita correttamente");
			}
			catch(Exception e){
				System.out.println("errore nell'inserimento nel DB");
				return false;
			}
			
		}
		//se arrivo alla fine tutto è andato bene
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Abilita> recuperaAbilitaDichiarate(String nickname) {
		//si fa una query su dichiarazione con where sul nick
		
		// @@@@@@@ da verificare la query!!!!!!!!!!!
		
		
		List<Abilita> listaAbilitaDichiarate;
		Query q;
		User user;
		
		//recupero lo user
		user = getUserInDichiarazione(nickname);
		
		q = gestoreDB.createQuery("SELECT d.abilitaDichiarata FROM Dichiarazione d WHERE d.userDichiarante.getNickname() = :nickname");
		q.setParameter("nickname", nickname);
		
		try{
			listaAbilitaDichiarate = (List<Abilita>) q.getResultList();
			System.out.println("numero abilita recuperate: " + listaAbilitaDichiarate.size());
			return listaAbilitaDichiarate;
		}
		catch (NoResultException e) {
			listaAbilitaDichiarate = null;
			System.out.println("non esiste alcuna abilità dichiarata");
			return listaAbilitaDichiarate;
		}
		
	}

	private User getUserInDichiarazione(String nickname){
		Query q;
		User user;
		
		q = gestoreDB.createQuery("SELECT u FROM User u WHERE u.nickname = :nickname");
		q.setParameter("nickname", nickname);

		try{
			user = (User) q.getSingleResult();
			System.out.println("user recuperato: " + user.getNickname());
			return user;
		}
		catch (NoResultException e) {
			user = null;
			System.out.println("lo user non esiste");
			return user;
		}
	}
}
