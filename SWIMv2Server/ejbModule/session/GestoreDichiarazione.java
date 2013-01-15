package session;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.jboss.ejb3.annotation.RemoteBinding;
import entity.Abilita;
import entity.Aiuto;
import entity.Dichiarazione;
import entity.Feedback;
import entity.User;

/**
 * Session Bean implementation class GestoreDichiarazione
 */
@Stateless
@RemoteBinding(jndiBinding = "GestoreDichiarazioneJNDI")
public class GestoreDichiarazione implements GestoreDichiarazioneRemote {

	@PersistenceContext(unitName = "swimv2_unit")
	private EntityManager gestoreDB;

    public GestoreDichiarazione() {
    	super();
    }


	@SuppressWarnings("unchecked")
	@Override
	public boolean setAbilitaDichiarate(String nickname, List<Integer> idAbilita) {
		List<Dichiarazione>	dichiarazioniDaSettare = new ArrayList<Dichiarazione>();
		List<Dichiarazione>	dichiarazioniDaRimuovere = new ArrayList<Dichiarazione>();
		User user = gestoreDB.find(User.class, nickname);
		Query query;
		
		//recupero le abilità associate agli id passati come parametro e creo gli oggetti Dichiarazione corrispondenti 
		
		for (Integer id: idAbilita) {
			Abilita abilitaDaSettare = gestoreDB.find(Abilita.class, id);
			query = gestoreDB.createQuery(
					"SELECT d " +
					"FROM Dichiarazione d " +
					"WHERE d.userDichiarante = :user " +
					"AND d.abilitaDichiarata = :abilita");
			query.setParameter("user", user);
			query.setParameter("abilita", abilitaDaSettare);
			Dichiarazione dichiarazione;
			try {
				//dichiarazione esistente -> dichiarazione non mutata
				dichiarazione = (Dichiarazione) query.getSingleResult();
			} catch (NoResultException noResultE) {
				//dichiarazione non esistente -> dichiarazione nuova o reinserita
				dichiarazione = new Dichiarazione();
				dichiarazione.setAbilitaDichiarata(abilitaDaSettare);
				dichiarazione.setUserDichiarante(user);
				initDichiarazione(dichiarazione);
			}
			dichiarazioniDaSettare.add(dichiarazione);
		}
		query = gestoreDB.createQuery(
				"SELECT d " +
				"FROM Dichiarazioni d " +
				"WHERE d.userDichiarante = :user");
		query.setParameter("user", user);
		dichiarazioniDaRimuovere = (List<Dichiarazione>) query.getResultList();
		for(Dichiarazione dichiarazione: dichiarazioniDaRimuovere) {
			if(dichiarazioniDaSettare.contains(dichiarazione)) {
				dichiarazioniDaSettare.remove(dichiarazione);
			} else {
				try {
					gestoreDB.remove(dichiarazione);
				} catch (Exception e) {
					System.out.println("Errore nella rimozione di una dichiarazione");
					return false;
				}
			}
		}
		for(Dichiarazione dichiarazione: dichiarazioniDaSettare) {
			try {
				gestoreDB.persist(dichiarazione);				
			} catch(Exception e) {
				System.out.println("Errore nell'inserimento di una dichiarazione");
				return false;				
			}
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	private void initDichiarazione(Dichiarazione dichiarazione) {
		
		//controlla se una dichiarazione è vergine per un user oppure no
		
		List<Aiuto> aiutiAbilita;
		Query query = gestoreDB.createQuery(
				"SELECT a " +
				"FROM Aiuto a " +
				"WHERE a.abilitaAiuto = :abilita");
		query.setParameter("abilita", dichiarazione.getAbilitaDichiarata());
		aiutiAbilita = (List<Aiuto>) query.getResultList();
		if(aiutiAbilita.isEmpty()) {
			dichiarazione.setNumeroFeedback(0);
			dichiarazione.setMediaValutazioni(0);
		} else {
			//dichiarazione vergine
			int numeroFeedback = 0;
			int sommaValutazioni = 0;
			for(Aiuto aiuto: aiutiAbilita) {
				Feedback feedback;
				feedback = gestoreDB.find(Feedback.class, aiuto.getCodiceAiuto());
				if( feedback != null){
					numeroFeedback ++;
					sommaValutazioni = sommaValutazioni + feedback.getValutazioneNumericaFeedback();
				}	
			}
			dichiarazione.setNumeroFeedback(numeroFeedback);
			
			if(numeroFeedback !=0){
				dichiarazione.setMediaValutazioni(sommaValutazioni / numeroFeedback);
			}
			else{
				dichiarazione.setMediaValutazioni(0);
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Abilita> recuperaAbilitaDichiarate(String nickname) {
		User user = gestoreDB.find(User.class, nickname);
		List<Abilita> abilitaDichiarate = new ArrayList<Abilita>();
		List<Dichiarazione> dichiarazioni;
		Query query = gestoreDB.createQuery(
				"SELECT d " +
				"FROM Dichiarazione d " +
				"WHERE d.userDichiarante = :user");
		query.setParameter("user", user);
		dichiarazioni = (List<Dichiarazione>) query.getResultList();
		for(Dichiarazione dichiarazione: dichiarazioni) {
			Abilita abilita;
			abilita = dichiarazione.getAbilitaDichiarata();
			abilitaDichiarate.add(abilita);
		}
		return abilitaDichiarate;
	}
}
