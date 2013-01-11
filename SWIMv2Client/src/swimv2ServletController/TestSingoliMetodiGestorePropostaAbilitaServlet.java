package swimv2ServletController;

import java.io.IOException;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exception.DBException;

import swimv2SessionBean.GestorePropostaAbilitaRemote;

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
			 * boolean inviaProposta(String nickUserProponente, String nomeAbilitaProposta, String descrizioneAbilitaProposta)
			 */
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


