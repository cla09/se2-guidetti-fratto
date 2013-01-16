package session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.ejb3.annotation.RemoteBinding;

import entity.Amicizia;
import entity.Data;
import entity.User;

/**
 * Session Bean implementation class GestoreAmicizia
 */
@Stateless
@RemoteBinding(jndiBinding = "GestoreAmiciziaJNDI")
public class GestoreAmicizia implements GestoreAmiciziaRemote {

	@PersistenceContext(unitName = "swimv2_unit")
	private EntityManager gestoreDB;
	
	private GestoreData gestoreData;
    /**
     * Default constructor. 
     */
    public GestoreAmicizia() {
    	super();
    }

	@Override
	public boolean inviaRichiestaAmicizia(String nicknameRichiedente, String nicknameDestinatario) {
		Amicizia amiciziaDaInserire = new Amicizia();
		gestoreData = new GestoreData();
		Data momentoRichiesta;
		User userRichiedente;
		User userDestinatario;
		
		//verifico se tra i due user è già presente un'amicizia o c'è una richiesta
		if( !(controllaAmicizia(nicknameRichiedente, nicknameDestinatario)) ){
			//gli utenti sono già amici o c'è una richiesta pendente
			return false;
		}
		//allora posso inserire la nuova richiesta
		
		//recupero i due user
		userRichiedente = gestoreDB.find(User.class, nicknameRichiedente);
		
		userDestinatario = gestoreDB.find(User.class, nicknameDestinatario);
		System.out.println("user recuperati: " + userRichiedente.getNickname() + "  " + userDestinatario.getNickname());
		//creo la data
		System.out.println("recupero data");
		momentoRichiesta = gestoreData.creaData();
		System.out.println("data recuperata: " + momentoRichiesta.getTimestamp());
		
		//setto gli attributi della nuova richiesta di amicizia
		amiciziaDaInserire.setUserRichiedenteAmiciza(userRichiedente);
		amiciziaDaInserire.setUserDestinatarioAmicizia(userDestinatario);
		amiciziaDaInserire.setMomentoRichiestaAmicizia(momentoRichiesta);
		amiciziaDaInserire.setMomentoAccettazioneAmicizia(null);
		
		//inserisco nel DB
		try{
			gestoreDB.persist(amiciziaDaInserire);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	protected boolean controllaAmicizia(String nicknameRichiedente, String nicknameDestinatario) {
		User userRichiedente;
		User userDestinatario;
		Amicizia amiciziaDaRecuperare;
		Query q;
		
		//recupero gli user
		userRichiedente = gestoreDB.find(User.class, nicknameRichiedente);
		userDestinatario = gestoreDB.find(User.class, nicknameDestinatario);
		
		q = gestoreDB.createQuery("SELECT a FROM Amicizia a WHERE a.userRichiedenteAmicizia = :userRichiedente AND a.userDestinatarioAmicizia = :userDestinatario");
		q.setParameter("userRichiedente", userRichiedente);
		q.setParameter("userDestinatario", userDestinatario);
		
		try{
			//i due utenti sono già amici o c'è una richiesta pendente
			amiciziaDaRecuperare = (Amicizia) q.getSingleResult();
			return false;
		}
		catch (NoResultException e) {
			//tra i due utente non c'è nè un'amicizia nè una richiesta pendente
			return true;
		}
	}

}
