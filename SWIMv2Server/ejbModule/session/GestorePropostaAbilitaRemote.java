package session;

import java.util.List;

import javax.ejb.Remote;

import entity.PropostaAbilita;

@Remote
public interface GestorePropostaAbilitaRemote {
	
	boolean inviaProposta(String nickUserProponente, String nomeAbilitaProposta, String descrizioneAbilitaProposta);
	
	List<PropostaAbilita> recuperaProposteAbilitaNonVisionate();
	
	List<PropostaAbilita> recuperaProposteAbilitaVisionate();
	
	boolean visionaProposta(int idProposta);
	
	boolean cancellaProposta(int idProposta);
	

}
