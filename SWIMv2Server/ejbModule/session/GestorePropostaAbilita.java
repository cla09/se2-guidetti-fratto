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
	public boolean inviaProposta(String nickUserProponente,	String nomeAbilitaProposta, String descrizioneAbilitaProposta){

		PropostaAbilita propostaAbilita = new PropostaAbilita();

		//setto gli attributi della proposta da memorizzare nel DB
		propostaAbilita.setNomePropostaAbilita(nomeAbilitaProposta);
		propostaAbilita.setDescrizionePropostaAbilita(descrizioneAbilitaProposta);
		propostaAbilita.setDefaultStatoProposta();

		//recupero lo user che ha proposto la nuova abilita
		User userProponente = gestoreDB.find(User.class, nickUserProponente);

		propostaAbilita.setUserProponenteAbilita(userProponente);

		/*
		 * stampa per vedificare se la proposta creata da aggiungere è corretta
    	System.out.println("\n"+nomeAbilitaProposta+"\n"+descrizioneAbilitaProposta+"\n"+nickUserProponente+"\n"+propostaAbilita.getStatoPropostaAbilita());
		 *
		 */

		gestoreDB.persist(propostaAbilita);

		return true;


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
	public boolean visionaProposta(int idProposta){

		PropostaAbilita propostaAbilitaDaModificare;

		propostaAbilitaDaModificare = gestoreDB.find(PropostaAbilita.class, idProposta);

		propostaAbilitaDaModificare.setStatoPropostaAbilita("visionata");

		gestoreDB.merge(propostaAbilitaDaModificare);

		return true;

	}

	
	@Override
	public boolean cancellaProposta(int idProposta) {

		PropostaAbilita propostaDaEliminare;

		propostaDaEliminare = gestoreDB.find(PropostaAbilita.class,	idProposta);


		gestoreDB.remove(propostaDaEliminare);

		return true;
	}


}
