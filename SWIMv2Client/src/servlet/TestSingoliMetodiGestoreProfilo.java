package servlet;

import java.io.IOException;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Admin;
import entity.Profilo;
import entity.User;

import session.GestoreAmiciziaRemote;
import session.GestoreProfiloRemote;

/**
 * Servlet implementation class TestSingoliMetodiGestoreProfilo
 */
public class TestSingoliMetodiGestoreProfilo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestSingoliMetodiGestoreProfilo() {
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

			GestoreProfiloRemote gestoreProfiloRemoto = (GestoreProfiloRemote) ctx.lookup("GestoreProfiloJNDI");

			System.out.println("****** sono nella servlet--- collegamento stabilito **********");

			 
			
			/*
			 * METODO: getRuolo
			 * 
			 * test1: ruolo user
			 * test2: ruolo admin
			 * test3: utente non esistente
			 * 
			 
			String ruoloDaRecuperare;
			String nickname1 = "cla09";
			String nickname2 = "admin";
			String nickname3 = "cla9";
			
			//test1
			ruoloDaRecuperare = gestoreProfiloRemoto.getRuolo(nickname1);
			System.out.println(nickname1 + " è un " + ruoloDaRecuperare);	//user
			
			//test2
			ruoloDaRecuperare = gestoreProfiloRemoto.getRuolo(nickname2);
			System.out.println(nickname2 + " è un " + ruoloDaRecuperare);	//admin
			
			//test3
			ruoloDaRecuperare = gestoreProfiloRemoto.getRuolo(nickname3);
			System.out.println(nickname3 + " è un " + ruoloDaRecuperare);	//non esiste
			
			//metodo funzionante
			
			*/
			
			
			
			/*
			 * METODO: controllaCredenziali
			 * 
			 * test1: credenziali corrette
			 * test2: nickname non valido
			 * test3: password non valida
			 
			 
			boolean risultato;
			String nicknameOK = "cla09";
			String nicknameKO = "cla9";
			String passwordOK = "claudiofratto";
			String passwordKO = "cf";
			
			//test1
			risultato = gestoreProfiloRemoto.controllaCredenziali(nicknameOK, passwordOK);
			if(risultato){
				System.out.println("nickname: " + nicknameOK + "  password: " + passwordOK + "sono credenziali valide");
			}
			else{
				System.out.println("nickname: " + nicknameOK + "  password: " + passwordOK + "sono non credenziali valide");
			}
			
			//test2
			risultato = gestoreProfiloRemoto.controllaCredenziali(nicknameKO, passwordOK);
			if(risultato){
				System.out.println("nickname: " + nicknameKO + "  password: " + passwordOK + " sono credenziali valide");
			}
			else{
				System.out.println("nickname: " + nicknameKO + "  password: " + passwordOK + " sono non credenziali valide");
			}
			
			//test3
			risultato = gestoreProfiloRemoto.controllaCredenziali(nicknameOK, passwordKO);
			if(risultato){
				System.out.println("nickname: " + nicknameOK + "  password: " + passwordKO + " sono credenziali valide");
			}
			else{
				System.out.println("nickname: " + nicknameOK + "  password: " + passwordKO + " sono non credenziali valide");
			}
			
			 // METODO TESTATO E FUNZIONANTE
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