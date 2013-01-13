package session;

import javax.ejb.Remote;

@Remote
public interface GestoreProfiloRemote {

	/**
	 * Il metodo serve per verificare la disponibilità di un nickname
	 *
	 * @param nickname 	nickname da verificare se è disponibile
	 * 
	 * @return true, se il nickname è disponibile; false, se il nickname non è disponibile
	 */
	public boolean controllaDisponibilitaNickname(String nickname);
	
	/**
	 * Il metodo serve per recuperare il ruolo associato all'utente che ha il nickname passato come parametro
	 * 
	 * @param nickname
	 * @return	{admin, user}
	 */
	public String getRuolo(String nickname); 
}
