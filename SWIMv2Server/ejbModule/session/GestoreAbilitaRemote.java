package session;

import java.util.List;

import javax.ejb.Remote;

import entity.Abilita;

@Remote
public interface GestoreAbilitaRemote {

	/**
	 * Il metodo serve per recuperare tutte le abilit� disponibili nel sistema
	 * 
	 * @return
	 */
	List<Abilita> recuperaAbilitaSistema();
	
	/**
	 * Il metodo serve per inserire una nuova abilita nel sistema
	 * 
	 * @param nome	Nome della nuova abilit�
	 * @param descrizione	Descrizione della nuova abilit�
	 * @param pathIcona		Percorso dove salvare/recuperare l'icona dell'abilit�
	 * 
	 * @return	true, se l'inserimento va a buon fine; false, altrimenti
	 */
	boolean creaAbilita(String nome, String descrizione, String pathIcona);
		
}
