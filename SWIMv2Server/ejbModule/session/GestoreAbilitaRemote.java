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
	
}
