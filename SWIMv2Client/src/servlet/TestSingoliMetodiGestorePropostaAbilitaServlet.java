package servlet;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.PropostaAbilita;

import session.GestorePropostaAbilitaRemote;

/**
 * Servlet implementation class TestSingoliMetodiGestorePropostaAbilitaServlet
 */
public class TestSingoliMetodiGestorePropostaAbilitaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestSingoliMetodiGestorePropostaAbilitaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			Context ctx = getInitialContext();

			GestorePropostaAbilitaRemote gestorePropostaAbilitaRemoto = (GestorePropostaAbilitaRemote) ctx.lookup("GestorePropostaAbilitaJNDI");
			
			System.out.println("****** sono nella servlet--- collegamento stabilito **********");
			
			/*
			//boolean inviaProposta(String nickUserProponente, String nomeAbilitaProposta, String descrizioneAbilitaProposta)
			System.out.println("######## verifico il funzionamento dell'inserimento di una nuova proposta ########");
			//parametri da inserire:
			String nicknameProponente = "franco";   //il nickname deve essere di uno user
			String nomeAbilitaProposta = "gigolo";
			String descrizioneAbilitaProposta = "per rapporti occasionali";
			
			try{
				gestorePropostaAbilitaRemoto.inviaProposta(nicknameProponente, nomeAbilitaProposta, descrizioneAbilitaProposta);
				
				System.out.println("\n\n@@@@@@ inserimento completato @@@@@@@\n\n");
			}
			catch(DBException e){
			}
			
			// METODO FUNZIONA CORRETTAMENTE
			 
			*/
			
			
			/*
			//List<PropostaAbilita> recuperaProposteAbilitaNonVisionate();
			System.out.println("\n\n\n######## verifico il funzionamento del recupero delle proposte non visionate ########\n");
			
			List<PropostaAbilita> proposteRecuperate;
			proposteRecuperate = gestorePropostaAbilitaRemoto.recuperaProposteAbilitaNonVisionate();
			
			System.out.println("Ci sono "+ proposteRecuperate.size() + " proposte di nuove abilita non visionate:\n");
			
			System.out.println("codice \t\t nome \t\t descrizione \t\t stato \t\t user");
			for(PropostaAbilita p: proposteRecuperate){
				System.out.println(p.getIdPropostaAbilita() +"\t\t" 
									+ p.getNomePropostaAbilita() + "\t\t"
									+ p.getDescrizionePropostaAbilita() + "\t\t"
									+ p.getStatoPropostaAbilita() + "\t\t" 
									+ p.getUserProponenteAbilita().getNickname());
			}
			
			// METODO FUNZIONA CORRETTAMENTE
			*/
			
			/*
			//List<PropostaAbilita> recuperaProposteAbilitaVisionate();
			System.out.println("\n\n\n######## verifico il funzionamento del recupero delle proposte visionate ########\n");
			
			List<PropostaAbilita> proposteRecuperate;
			proposteRecuperate = gestorePropostaAbilitaRemoto.recuperaProposteAbilitaVisionate();
			
			System.out.println("Ci sono "+ proposteRecuperate.size() + " proposte di nuove abilita già visionate:\n");
			
			System.out.println("codice \t\t nome \t\t descrizione \t\t stato \t\t user");
			for(PropostaAbilita p: proposteRecuperate){
				System.out.println(p.getIdPropostaAbilita() +"\t\t" 
									+ p.getNomePropostaAbilita() + "\t\t"
									+ p.getDescrizionePropostaAbilita() + "\t\t"
									+ p.getStatoPropostaAbilita() + "\t\t" 
									+ p.getUserProponenteAbilita().getNickname());
			}
			
			// METODO FUNZIONA CORRETTAMENTE
			
			*/
			
			/*
			//boolean visionaProposta(int idProposta) throws DBException
			System.out.println("######## verifico il funzionamento della visione di una nuova proposta ########");
			//parametri da inserire:
			int idPropostaDaVisionare = 2; //deve essere recuperato dalla jsp e deve corrispondere ad una proposta nonVisionata
			
			try{
				gestorePropostaAbilitaRemoto.visionaProposta(idPropostaDaVisionare);
				
				System.out.println("\n\n@@@@@@ modifica completata correttamente @@@@@@@\n\n");
			}
			catch(DBException e){
			}
			
			// METODO FUNZIONA CORRETTAMENTE
			
			*/
			
			/*
			//boolean cancellaProposta(int idProposta) throws DBException;
			System.out.println("######## verifico il funzionamento dell'eliminazione di una nuova proposta ########");
			//parametri da inserire:
			int idPropostaDaEliminare = 2; //deve essere recuperato dalla jsp e deve corrispondere ad una proposta 
			
			try{
				gestorePropostaAbilitaRemoto.cancellaProposta(idPropostaDaEliminare);
				
				System.out.println("\n\n@@@@@@ proposta eliminata correttamente @@@@@@@\n\n");
			}
			catch(DBException e){
			}
			
			// METODO FUNZIONA CORRETTAMENTE
			*/
			
			
			
			
			

		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}


	static public Context getInitialContext() throws Exception{

		Hashtable<String,String> env = new Hashtable<String,String>();

		env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		env.put(Context.PROVIDER_URL, "localhost:1099");

		return new InitialContext(env);

	}
}


