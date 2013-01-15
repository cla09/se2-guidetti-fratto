package session;

import java.util.List;

import javax.ejb.Remote;

import entity.Abilita;

@Remote
public interface GestoreAbilitaRemote {

	/**
	 * Il metodo serve per recuperare tutte le abilità disponibili nel sistema
	 * 
	 * @return
	 */
	List<Abilita> recuperaAbilitaSistema();
	
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
	 * Il metodo serve per inserire una nuova abilita nel sistema
	 * 
	 * @param nome	Nome della nuova abilità
	 * @param descrizione	Descrizione della nuova abilitò
	 * @param pathIcona		Percorso dove salvare/recuperare l'icona dell'abilità
	 * 
	 * @return	true, se l'inserimento va a buon fine; false, altrimenti
	 */
	boolean creaAbilita(String nome, String descrizione, String pathIcona);
	
	/**
	 * Il metodo serve per recuperare tutte le abilita dichiarate da uno user
	 * 
	 * @param nickname	dello user
	 * 
	 * @return	Lista delle abilita dichiarate dallo user
	 */
	List<Abilita> recuperaAbilitaDichiarate(String nickname);
	
}
