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

import session.GestoreAdminRemote;

/**
 * Servlet implementation class TestSingoliMetodiGestoreAdmin
 */
public class TestSingoliMetodiGestoreAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestSingoliMetodiGestoreAdmin() {
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

    		GestoreAdminRemote gestoreAdminRemoto = (GestoreAdminRemote) ctx.lookup("GestoreAdminJNDI");

    		System.out.println("****** sono nella servlet--- collegamento stabilito **********");

    		/*
    		 * METODO: getAdmin()
    		 * 
    		 * test: ottenere tutte le informazioni legate al profilo di amministratore
    		 
    		Admin adminDaRecuperare;
    		adminDaRecuperare = gestoreAdminRemoto.getAdmin();
    		
    		System.out.println("******** le informazioni dell'admin sono: **********\n" + 
    							"nick: " + adminDaRecuperare.getNickname() +
    							"\npwd: " + adminDaRecuperare.getPassword() +
    							"\nnome: " + adminDaRecuperare.getNome() +
    							"\ncognome: " + adminDaRecuperare.getCognome() +
    							"\npath avatar: " + adminDaRecuperare.getAvatar()
    							);
    		
    		//metodo funzionante: test superato
    		/*
    		
    		/*
			 * METODO: modificaInformazioniAdmin
			 * 
			 * test: modifico la password, la mail dell'admin
			 
			boolean risultatoModifica;
			
			Admin adminAggiornato = new Admin();
			String nickname = "admin";
			String password = "0000";
			String email = "admin.com";
			String nome = "inserici nome";
			String cognome = "inserisci cognome";
			String percorsoAvatar = null; 
			
			
			adminAggiornato.setNickname(nickname);
			adminAggiornato.setPassword(password);
			adminAggiornato.setEmail(email);
			adminAggiornato.setNome(nome);
			adminAggiornato.setCognome(cognome);
			adminAggiornato.setAvatar(percorsoAvatar);
			
			risultatoModifica = gestoreAdminRemoto.modificaInformazioniAdmin(adminAggiornato);
			
			if(risultatoModifica){
				System.out.println("informazioni modificate correttamente... la nuova password è: " + adminAggiornato.getPassword());
			}
			else{
				System.out.println("ERRORE: informazioni non modificate");
			}
			
			//metodo funzionante
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
