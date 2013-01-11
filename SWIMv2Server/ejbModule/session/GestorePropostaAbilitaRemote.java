package session;

import java.util.List;

import javax.ejb.Remote;

import entity.PropostaAbilita;

@Remote
public interface GestorePropostaAbilitaRemote {
	
	/**
	 * Il metodo si occupa di inserire nel database una proposta di nuova abilità
	 * 
	 * @param nickUserProponente	nickname dello user proponente
	 * @param nomeAbilitaProposta	nome dell'abilità proposta
	 * @param descrizioneAbilitaProposta	descrizione dell'abilità proposta
	 */
	void inviaProposta(String nickUserProponente, String nomeAbilitaProposta, String descrizioneAbilitaProposta);
	
	/**
	 * Il metodo si occupa di recuperare tutte le proposte di abilità presenti nel database che sono marcate come "nonVisionate"
	 * 
	 * @return	Restituisce una lista di ProposteAbilita
	 */
	List<PropostaAbilita> recuperaProposteAbilitaNonVisionate();
	
	/**
	 * Il metodo si occupa di recuperare tutte le proposte di abilità presenti nel database che sono marcate come "visionate"
	 * 
	 * @return	Restituisce una lista di ProposteAbilita
	 */
	List<PropostaAbilita> recuperaProposteAbilitaVisionate();
	
	/**
	 * Il metodo si occupa di aggiornare il campo 'stato_proposta' facendolo passare da 'nonVisionata' a 'visionata'
	 * 
	 * @param idProposta	Codice identificativo della proposta di cui si vuole cambiare lo stato
	 */
	void visionaProposta(int idProposta);
	
	/**
	 * Il metodi si occupa di eliminare una proposta di nuova abilità dal database
	 *
	 * @param idProposta	Codice identificativo della proposta da eliminare
	 */
	void cancellaProposta(int idProposta);
	

}
