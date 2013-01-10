package swimv2SessionBean;

import java.util.List;

import javax.ejb.Remote;

import swimv2EntityBean.PropostaAbilita;
import exception.DBException;

@Remote
public interface GestorePropostaAbilitaRemote {
	
	boolean inviaProposta(String nickUserProponente, String nomeAbilitaProposta, String descrizioneAbilitaProposta) throws DBException;
	
	List<PropostaAbilita> recuperaProposteAbilita();	

}
