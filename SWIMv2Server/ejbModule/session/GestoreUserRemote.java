package session;

import javax.ejb.Remote;

import entity.User;

@Remote
public interface GestoreUserRemote {
	
	/**
	 * Il metodo serve per inserire nel sistema un nuovo utente le cui informazioni sono passate come parametri
	 * 
	 * @param nickname
	 * @param password
	 * @param email
	 * @param nome
	 * @param cognome
	 * @param percorsoAvatar
	 * @param citta
	 * @param sesso
	 * @param annoNascita
	 * 
	 * @return	ritorna true se l'operazione è andata a buon fine, false altrimenti
	 */
	boolean registra(String nickname, String password, String email, String nome, String cognome, String percorsoAvatar, String citta, String sesso, int annoNascita);

	/**
	 * Il metodo serve per recuperare il profilo associato all'utente che ha il nickname passato come parametro
	 * 
	 * @param nickname
	 * @return
	 */
	User getUser(String nickname);
}
