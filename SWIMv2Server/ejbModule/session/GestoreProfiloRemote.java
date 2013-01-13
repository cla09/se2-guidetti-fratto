package session;

import javax.ejb.Remote;

@Remote
public interface GestoreProfiloRemote {

	
	
	/**
	 * Il metodo serve per recuperare il ruolo associato all'utente che ha il nickname passato come parametro
	 * 
	 * @param nickname
	 * @return	{admin, user}
	 */
	public String getRuolo(String nickname); 
	
	/**
	 * Il metodo si occupa di verificare se le credenziali inserite da un utente corrispondono a delle credenziali valide
	 * 
	 * @param nickname
	 * @param password
	 * 
	 * @return	true, se le credenziali sono valide; false altrimenti
	 */
	boolean controllaCredenziali(String nickname, String password);
}
