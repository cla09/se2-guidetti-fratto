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
import session.GestoreDichiarazioneLocal;

/**
 * Servlet implementation class TestSingoliMetodiGestoreDichiarazione
 */
public class TestSingoliMetodiGestoreDichiarazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestSingoliMetodiGestoreDichiarazione() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Context ctx = getInitialContext();
		
			
			GestoreDichiarazioneLocal gestoreDichiarazioneLocal = (GestoreDichiarazioneLocal) ctx.lookup("GestoreDichiarazione/local");
			
			System.out.println("****** sono nella servlet--- collegamento stabilito **********");
			
			/*
			 * METODO setAbilitaDichiarate
			 * 
			 * test
			 */

			/*
			 * METODO getAbilitaDichiarate
			 * 
			 * test1: recupera tutte le abilità dichiarate da cla09 (ha dichiarato qualche abilità)
			 * 
			 

			List<Abilita> abilitaDichiarate;
			String nickname;

			//test1
			nickname = "cla09";		
			abilitaDichiarate = gestoreDichiarazioneLocal.recuperaAbilitaDichiarate(nickname);
			System.out.println("il numero di abilità dichiarate: " + abilitaDichiarate.size());
			if(abilitaDichiarate != null){
				System.out.println("lo user " + nickname + " ha dichiarato le seguenti abilità:");
				for(Abilita abilita: abilitaDichiarate){
					System.out.println("abilita: " + abilita.getNome());
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
		
		*/
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