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
	public String getRuolo(String nickname) {
		String ruoloDaRecuperare;
		Profilo profiloDaRecuperare;
		
		if(nickname.equals("admin")){
			ruoloDaRecuperare = "admin";
		}
		else{
			//sarà uno user o non esiste
			profiloDaRecuperare = getProfilo(nickname);
			if(profiloDaRecuperare != null){
				ruoloDaRecuperare = "user";
			}
			else{
				ruoloDaRecuperare = null;
			}
		}
		
		return ruoloDaRecuperare;
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
			profiloDaRecuperare = null;
			return profiloDaRecuperare;
		}
	}


	@Override
	public boolean controllaCredenziali(String nickname, String password) {
		Profilo profiloDaRecuperare;

		Query q = gestoreDB.createQuery("SELECT p FROM Profilo p WHERE p.nickname = :nickname AND p.password = :password");
		q.setParameter("nickname", nickname);
		q.setParameter("password", password);
			
		try{
			profiloDaRecuperare = (Profilo) q.getSingleResult();
			return true;
		}
		catch (NoResultException e) {
			return false;
		}
	}


	
}
