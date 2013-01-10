package swimv2SessionBean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.*;

import org.jboss.ejb3.annotation.RemoteBinding;

import swimv2EntityBean.PropostaAbilita;
import swimv2EntityBean.User;
import exception.DBException;

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
    public boolean inviaProposta(String nickUserProponente,	String nomeAbilitaProposta, String descrizioneAbilitaProposta)	throws DBException {

    	PropostaAbilita propostaAbilita = new PropostaAbilita();

    	//setto gli attributi della proposta da memorizzare nel DB
    	propostaAbilita.setNomePropostaAbilita(nomeAbilitaProposta);
    	propostaAbilita.setDescrizionePropostaAbilita(descrizioneAbilitaProposta);
    	propostaAbilita.setDefaultStatoProposta();

    	//recupero lo user che ha proposto la nuova abilita
    	User userProponente = getUserProponente(nickUserProponente);

    	propostaAbilita.setUserProponenteAbilita(userProponente);
    	
    	/*
    	 * stampa per vedificare se la proposta creata da aggiungere è corretta
    	System.out.println("\n"+nomeAbilitaProposta+"\n"+descrizioneAbilitaProposta+"\n"+nickUserProponente+"\n"+propostaAbilita.getStatoPropostaAbilita());
    	*
    	*/
    	
    	
    	try{
    		gestoreDB.persist(propostaAbilita);
    		gestoreDB.flush();

    		return true;
    	}
    	catch(Exception e){
    		throw new DBException();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PropostaAbilita> recuperaProposteAbilita() {
		
		List<PropostaAbilita> proposteAbilitaRecuperate;

		Query q = gestoreDB.createQuery("SELECT p FROM PropostaAbilita p");
		proposteAbilitaRecuperate = (List<PropostaAbilita>) q.getResultList(); 
		
		/*
		 * Stampa di prova per verificare che il metodo recupera tutte le proposte presenti nel db
		 * 
		 * for (PropostaAbilita p: proposteAbilitarecuperate){
				if(p.getStatoPropostaAbilita().equals("nonVisionata")){
					System.out.println("userProponente: "+ p.getUserProponenteAbilita().getNickname()+ 
						"nome abilita proposta: "+ p.getNomePropostaAbilita());
				}
			}
		 */
		
		return proposteAbilitaRecuperate;

	}

	/*
	 * questo metodo serve per recuperare un oggetto user dal suo nickname
	 */
	private User getUserProponente(String nickname){
		Query q = gestoreDB.createQuery("SELECT u FROM User u WHERE u.nickname = :nickname");  //:nickname è un parametro
		q.setParameter("nickname", nickname);
		
		Object risultatoQuery;
		
		risultatoQuery = q.getSingleResult();
		
		return (User)risultatoQuery;
	
	}
    

}
