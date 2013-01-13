package session;

import javax.ejb.Remote;

import entity.User;

@Remote
public interface GestoreUserRemote {
	
	/**
	 * Il metodo serve per verificare la disponibilità di un nickname
	 *
	 * @param nickname 	nickname da verificare se è disponibile
	 * 
	 * @return true, se il nickname è disponibile; false, se il nickname non è disponibile
	 */
	public boolean controllaDisponibilitaNickname(String nickname);
	
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
	
	/**
	 * Il metodo server per rendere effettive le modifiche degli attributi dello user passato come parametro
	 * 
	 * @param userModificato	contiene tutti gli attributi aggiornati
	 * 
	 * @return	true, se le modifiche vengono portate a termine correttamente; false altrimenti
	 */
	boolean modificaInformazioniUser(User userModificato);
	
	/**
	 * Il metodo serve per rimuovere dalla base dati tutte le informazioni legate allo user associato al nickname passato come parametro
	 * 
	 * @param nickname
	 * 
	 * @return	true, se la cancellazione è andata a buon fine; false, altrimenti
	 * 
	 */
	boolean confermaCancellazioneUser(String nickname);
}
