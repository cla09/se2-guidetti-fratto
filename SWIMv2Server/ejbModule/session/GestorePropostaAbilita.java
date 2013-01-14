package session;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.*;

import org.jboss.ejb3.annotation.RemoteBinding;

import entity.PropostaAbilita;
import entity.User;

/**
 * Session Bean implementation class GestorePropostaAbilita
 */
@Stateless
@RemoteBinding(jndiBinding = "GestorePropostaAbilitaJNDI")
public class GestorePropostaAbilita implements GestorePropostaAbilitaRemote {

	@PersistenceContext(unitName = "swimv2_unit")
	private EntityManager gestoreDB;

	/**
	 * Default constructor. 
	 */
	public GestorePropostaAbilita() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void inviaProposta(String nickUserProponente, String nomeAbilitaProposta, String descrizioneAbilitaProposta){

		PropostaAbilita propostaAbilita = new PropostaAbilita();

		//setto gli attributi della proposta da memorizzare nel DB
		propostaAbilita.setNomePropostaAbilita(nomeAbilitaProposta);
		propostaAbilita.setDescrizionePropostaAbilita(descrizioneAbilitaProposta);
		propostaAbilita.setDefaultStatoProposta();

		//recupero lo user che ha proposto la nuova abilita
		User userProponente = gestoreDB.find(User.class, nickUserProponente);

		propostaAbilita.setUserProponenteAbilita(userProponente);

		gestoreDB.persist(propostaAbilita);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<PropostaAbilita> recuperaProposteAbilitaNonVisionate() {

		List<PropostaAbilita> proposteAbilitaRecuperate;

		Query q = gestoreDB.createQuery("SELECT p FROM PropostaAbilita p WHERE p.statoProposta = :stato");
		q.setParameter("stato", "nonVisionata");
		proposteAbilitaRecuperate = (List<PropostaAbilita>) q.getResultList(); 

		return proposteAbilitaRecuperate;

	}



	@SuppressWarnings("unchecked")
	@Override
	public List<PropostaAbilita> recuperaProposteAbilitaVisionate() {

		List<PropostaAbilita> proposteAbilitaRecuperate;

		Query q = gestoreDB.createQuery("SELECT p FROM PropostaAbilita p WHERE p.statoProposta = :stato");
		q.setParameter("stato", "visionata");
		proposteAbilitaRecuperate = (List<PropostaAbilita>) q.getResultList(); 

		return proposteAbilitaRecuperate;

	}

	@Override
	public void visionaProposta(int idProposta){

		PropostaAbilita propostaAbilitaDaModificare;
		propostaAbilitaDaModificare = gestoreDB.find(PropostaAbilita.class, idProposta);
		propostaAbilitaDaModificare.setStatoPropostaAbilita("visionata");

		gestoreDB.merge(propostaAbilitaDaModificare);
		
	}

	
	@Override
	public void cancellaProposta(int idProposta) {

		PropostaAbilita propostaDaEliminare;
		propostaDaEliminare = gestoreDB.find(PropostaAbilita.class,	idProposta);

		gestoreDB.remove(propostaDaEliminare);

	}


}
