package session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.ejb3.annotation.RemoteBinding;

import entity.Profilo;

/**
 * Session Bean implementation class GestoreProfilo
 * 
 */
@Stateless
@RemoteBinding(jndiBinding = "GestoreProfiloJNDI")
public class GestoreProfilo implements GestoreProfiloRemote {

	@PersistenceContext(unitName = "swimv2_unit")
	private EntityManager gestoreDB;
	
	
    public GestoreProfilo() {
        // TODO Auto-generated constructor stub
    }

    
	@Override
	public boolean controllaDisponibilitaNickname(String nickname) {
		System.out.println("verifico se c'è l'utente");
		
		if(getProfilo(nickname) != null){
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
	public String getRuolo(String nickname) {
		String ruoloDaRecuperare;

		Query q = gestoreDB.createQuery("SELECT p.ruolo FROM Profilo p WHERE p.nickname = :nickname");
		q.setParameter("nickname", nickname);

		try{
			ruoloDaRecuperare = (String) q.getSingleResult();
			return ruoloDaRecuperare;
		}
		catch (NoResultException e) {
			System.out.println("utente non disponibile");
			return null;
		}
	}

	
	/*
	 * serve per recuperare il profilo dell'utente associato al nickname passato come parametro
	 */
	private Profilo getProfilo(String nickname){
		Profilo profiloDaRecuperare;
		
		Query q = gestoreDB.createQuery("SELECT p FROM Profilo p WHERE p.nickname = :nickname");
		q.setParameter("nickname", nickname);
		
		try{
			profiloDaRecuperare = (Profilo) q.getSingleResult();
			return profiloDaRecuperare;
		}
		catch (NoResultException e) {
			return null;
		}
	}


	
}
