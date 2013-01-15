package session;

import java.util.List;
import javax.ejb.Remote;
import entity.Abilita;

@Remote
public interface GestoreDichiarazioneRemote {
	
	/**
	 * Il metodo si occupa di settare (modificare) l'insieme delle abilità dichiarate di uno user
	 *  
	 * @param nickname		dello user	
	 * @param idAbilita		lista di indici corrispondenti agli id delle abilità da settare
	 * 
	 * @return	true, se la modifica va a buon fine; false altrimenti
	 */
	boolean setAbilitaDichiarate(String nickname, List<Integer> idAbilita);
	
	/**
	 * Il metodo serve per recuperare tutte le abilita dichiarate da uno user
	 * 
	 * @param nickname	dello user
	 * 
	 * @return	Lista delle abilita dichiarate dallo user
	 */
	List<Abilita> recuperaAbilitaDichiarate(String nickname);

}
