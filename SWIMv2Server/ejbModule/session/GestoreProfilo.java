package session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.ejb3.annotation.RemoteBinding;

import entity.Admin;
import entity.Profilo;
import entity.User;

/**
 * Session Bean implementation class GestoreProfilo
 */
@Stateless
@RemoteBinding(jndiBinding = "GestoreProfiloJNDI")
public class GestoreProfilo implements GestoreProfiloRemote {

	@PersistenceContext(unitName = "swimv2_unit")
	private EntityManager gestoreDB;
	
    /**
     * Default constructor. 
     */
    public GestoreProfilo() {
        // TODO Auto-generated constructor stub
    }
    
    
	@Override
	public boolean registra(String nickname, String password, String email, String nome, String cognome, String percorsoAvatar, 
							String citta, String sesso, int annoNascita) {
		
		if( ! controllaDisponibilitaNickname(nickname)){
			//nickname non disponibile
			return false;
		}
		
		Profilo nuovoUser = new User();
		
		nuovoUser.setNickname(nickname);
		nuovoUser.setPassword(password);
		nuovoUser.setEmail(email);
		nuovoUser.setNome(nome);
		nuovoUser.setCognome(cognome);
		nuovoUser.setAvatarProfilo(percorsoAvatar);
		nuovoUser.setCitta(citta);
		nuovoUser.setSesso(sesso);
		nuovoUser.setAnnoNascita(annoNascita);
		
		
		try{
			gestoreDB.persist(nuovoUser);
			return true;
		}
		catch(Exception e){
			System.out.println("errore nell'inserimento nel DB");
			return false;
		}
		
	}
	
	/*
	 * il metodo serve per verificare la disponibilità di un nickname
	 */
	private boolean controllaDisponibilitaNickname(String nickname){
		System.out.println("verifico se c'è l'utente");
		
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
	public Profilo recuperaDatiProfilo(String nickname) {
		Profilo profiloDaRecuperare;
		
		//recupero il ruolo
		String ruolo;
		ruolo = getRuolo(nickname);
		
		if(ruolo.equals("admin")){
			//devo recuperare i dati relativi all'admin
			profiloDaRecuperare = new Admin();
			profiloDaRecuperare = getAdmin();
			
			return profiloDaRecuperare;
		}
		else{
			if(ruolo.equals("user")){
				//devo recuperare i dati relativi allo user
				profiloDaRecuperare = new User();
				profiloDaRecuperare = getUser(nickname);
				
				return profiloDaRecuperare;
			}
			else{
				//non esiste uno user associato a quel nickname
				return null;
			}
		}
		
	}
	
	/*
	 * il metodo serve per recuperare il ruolo del profilo associato al nickname passato come parametro
	 */
	private String getRuolo(String nickname){
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
	 * serve per recuperare il profilo dell'amministratore
	 */
	private Admin getAdmin(){
		Admin adminDaRecuperare;
		
		Query q = gestoreDB.createQuery("SELECT a FROM Admin a WHERE a.nickname = :nickname");
		q.setParameter("nickname", "admin");
		
		try{
			adminDaRecuperare = (Admin) q.getSingleResult();
			return adminDaRecuperare;
		}
		catch (NoResultException e) {
			return null;
		}
	}
	
	/*
	 * serve per recuperare il profilo dell'utente associato al nickname passato come parametro
	 */
	private User getUser(String nickname){
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
	
}
