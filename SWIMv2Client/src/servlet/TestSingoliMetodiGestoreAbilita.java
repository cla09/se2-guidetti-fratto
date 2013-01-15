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

import entity.Abilita;

import session.GestoreAbilitaRemote;
import session.GestoreAmiciziaRemote;

/**
 * Servlet implementation class TestSingoliMetodiGestoreAbilita
 */
public class TestSingoliMetodiGestoreAbilita extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestSingoliMetodiGestoreAbilita() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Context ctx = getInitialContext();

			GestoreAbilitaRemote gestoreAbilitaRemoto = (GestoreAbilitaRemote) ctx.lookup("GestoreAbilitaJNDI");

			System.out.println("****** sono nella servlet--- collegamento stabilito **********");

			/*
			 * METODO: recuperaAbilitaSistema
			 * 
			 * test: deve mostrare tutte le abilita presenti nel database
			 
			List<Abilita> abilitaDisponibili;
			
			abilitaDisponibili = gestoreAbilitaRemoto.recuperaAbilitaSistema();
			
			for(Abilita a: abilitaDisponibili){
				System.out.println(a.getNome());
			}
			
			//metodo funzionante
			 */
			
			/*
			 * METODO: creaAbilita
			 * 
			 * test: inserisci una nuova abilita nel sistema
			 * 
			 
			boolean risultatoAggiugiAbilita;
			String nome = "portiere";
			String descrizione = "disponibile notturni";
			String pathIcona = null;
			
			risultatoAggiugiAbilita = gestoreAbilitaRemoto.creaAbilita(nome, descrizione, pathIcona);
			
			System.out.println("risultato inserimento: " + risultatoAggiugiAbilita);

			// metodo funzionante
			
			*/
			
			/*
			 * METODO setAbilitaDichiarate
			 * 
			 * da testare
			 */
			
			/*
			 * METODO getAbilitaDichiarate
			 * 
			 * test1: recupera tutte le abilità dichiarate da cla09 (ha dichiarato qualche abilità)
			 * test2: recupera abilita utente che non ha dichiarata nulla
			 */
			
			List<Abilita> abilitaDichiarate;
			String nickname;
			
			//test1
			nickname = "cla09";		
			abilitaDichiarate = gestoreAbilitaRemoto.recuperaAbilitaDichiarate(nickname);
			
			if(abilitaDichiarate != null){
				System.out.println("lo user " + nickname + "ha dichiarato le seguenti abilità:");
				for(Abilita a: abilitaDichiarate){
					System.out.println("abilita: " + a.getNome());
				}
			}
			else{
				System.out.println("lo user non ha dichiarato alcuna abilita");
			}
			
			//test2
			nickname = "cla0"; 		
			abilitaDichiarate = gestoreAbilitaRemoto.recuperaAbilitaDichiarate(nickname);
			
			if(abilitaDichiarate != null){
				System.out.println("lo user " + nickname + "ha dichiarato le seguenti abilità:");
				for(Abilita a: abilitaDichiarate){
					System.out.println("abilita: " + a.getNome());
				}
			}
			else{
				System.out.println("lo user non ha dichiarato alcuna abilita");
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