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


	@Override
	public boolean setAbilitaDichiarate(String nickname, List<Integer> idAbilita) {
		List<Dichiarazione>	dichiarazioniDaSettare = new ArrayList<Dichiarazione>();
		User userDaSettare = gestoreDB.find(User.class, nickname);
		
		//recupero le abilità associate agli id passati come parametro e creo gli oggetti Dichiarazione corrispondenti 
		
		for (Integer id: idAbilita) {
			Abilita abilitaDaRecuperare = gestoreDB.find(Abilita.class, id);
			Query query = gestoreDB.createQuery(
					"SELECT d " +
					"FROM Dichiarazione d " +
					"WHERE d.userDichiarante = :user " +
					"AND d.abilitaDichiarata = :abilita");
			query.setParameter("user", userDaSettare);
			query.setParameter("abilita", abilitaDaRecuperare);
			Dichiarazione dichiarazione;
			try {
				//dichiarazione esistente -> dichiarazione non mutata
				dichiarazione = (Dichiarazione) query.getSingleResult();
			} catch (NoResultException noResultE) {
				//dichiarazione non esistente -> dichiarazione nuova o reinserita
				dichiarazione = new Dichiarazione();
				dichiarazione.setAbilitaDichiarata(abilitaDaRecuperare);
				dichiarazione.setUserDichiarante(userDaSettare);
				initDichiarazione(dichiarazione);
			}
			dichiarazioniDaSettare.add(dichiarazione);
		}
		userDaSettare.setDichiarazioni(dichiarazioniDaSettare);
		try {
			//stampo le dichiarazioni dello user
			List<Dichiarazione> dichia = userDaSettare.getDichiarazioni();
			
			for(Dichiarazione d: dichia){
				System.out.println("dichiarazione: " + d.getAbilitaDichiarata().getNome());
			}
			
			List<Dichiarazione> dichiarazioneUserOld = userDaSettare.getDichiarazioni();
						
			gestoreDB.merge(userDaSettare);
		} catch (Exception e) {
			System.out.println("errore nell'aggiornamento nel DB");
			return false;
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

	@Override
	public List<Abilita> recuperaAbilitaDichiarate(String nickname) {
		User user = gestoreDB.find(User.class, nickname);
		List<Abilita> abilitaDichiarate = new ArrayList<Abilita>();
		List<Dichiarazione> dichiarazioni;
		dichiarazioni = user.getDichiarazioni();
		
		for(Dichiarazione dichiarazione: dichiarazioni) {
			Abilita abilita;
			abilita = dichiarazione.getAbilitaDichiarata();
			abilitaDichiarate.add(abilita);
		}
		return abilitaDichiarate;
	}
}
