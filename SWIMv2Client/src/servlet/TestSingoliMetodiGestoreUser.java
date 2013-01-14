package servlet;

import java.io.IOException;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.User;

import session.GestoreProfiloRemote;
import session.GestoreUserRemote;

/**
 * Servlet implementation class TestSingoliMetodiGestoreUser
 */
public class TestSingoliMetodiGestoreUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TestSingoliMetodiGestoreUser() {
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

			GestoreUserRemote gestoreUserRemoto = (GestoreUserRemote) ctx.lookup("GestoreUserJNDI");

			System.out.println("****** sono nella servlet--- collegamento stabilito **********");

			/*
			 * METODO: controllaDisponibilitaNickname 
			 * 
			 * test1: verificare che un nick non esiste
			 * test2: verificare che un nick è già utilizzato
			 
			boolean risultato;
			String nickname1 = "cla9";
			String nickname2 = "cla09";

			//test1
			risultato = gestoreUserRemoto.controllaDisponibilitaNickname(nickname1);  	//non presente nel DB --- risultato->true
			if(risultato){
				System.out.println("il nickname '" + nickname1 + "' è disponibile");
			}

			//test2
			risultato = gestoreUserRemoto.controllaDisponibilitaNickname(nickname2);	//presente nel DB --- risultato->false
			if(!risultato){
				System.out.println("il nickname '" + nickname2 + "' non è disponibile");
			}

			//metodo funzionante
			*/
			
			
			/*
			 * METODO registra
			 * 
			 * test1: inserimento nuovo utente (con nickname disponibile)
			 * test2: inserimento non consentito (nickname non disponibile)
			 * 
			 * provo ad inserire due volte l'utente pippo
			 * 
			 
			String nickname = "pippo";
			String password = "pluto";
			String email = "pippo.com";
			String nome = "filippo";
			String cognome = "rocchi";
			String percorsoAvatar = "percorsoProva"; 
			String citta = "cz";
			String sesso = "M";
			int annoNascita = 1990; 
						
			boolean inserimento1;
			boolean inserimento2;
			
			inserimento1 = gestoreUserRemoto.registra(nickname, password, email, nome, cognome, percorsoAvatar, citta, sesso, annoNascita);
			
			if(inserimento1){
				System.out.println("user " + nickname + " inserito correttamente");
			}
			else{
				System.out.println("user " + nickname + " non inserito");
			}

			inserimento2 = gestoreUserRemoto.registra(nickname, password, email, nome, cognome, percorsoAvatar, citta, sesso, annoNascita);

			if(inserimento2){
				System.out.println("user " + nickname + " inserito correttamente");
			}
			else{
				System.out.println("user " + nickname + " non inserito");
			}
			
			//METODO FUNZIONANATE
			
			*/
			
			/*
			 * METODO: getUser
			 * 
			 * test1: ottenere tutte le informazioni legate ad uno user esistente
			 * test2: messaggio di errore se lo user non esiste
			 * 
			 */
			String nickname1;
			String nickname2;
			User user;
			
			nickname1 = "cla09";
			nickname2 = "cla9";
			
			user = gestoreUserRemoto.getUser(nickname1);	//user esistente
			if(user != null){
				
	    		System.out.println("******** le informazioni dello user sono sono: **********\n" + 
	    							"nick: " + user.getNickname() +
	    							"\npwd: " + user.getPassword() +
	    							"\nnome: " + user.getNome() +
	    							"\ncognome: " + user.getCognome() +
	    							"\npath avatar: " + user.getAvatar() +
	    							"\ncitta: " + user.getCitta() +
	    							"\nsesso :" + user.getSesso() +
	    							"\nanno nascita: " + user.getAnnoNascita()
	    							);
	    		System.out.println("il numero di proposta abilita: " + user.getProposteAbilita().size());
			}
			else{
				System.out.println("non esiste nessun utente");
			}

			user = gestoreUserRemoto.getUser(nickname2);
			if(user != null){

				System.out.println("******** le informazioni dello user sono sono: **********\n" + 
										"nick: " + user.getNickname() +
										"\npwd: " + user.getPassword() +
										"\nnome: " + user.getNome() +
										"\ncognome: " + user.getCognome() +
										"\npath avatar: " + user.getAvatar() +
										"\ncitta: " + user.getCitta() +
										"\nsesso :" + user.getSesso() +
										"\nanno nascita: " + user.getAnnoNascita()
									);
			}
			else{
				System.out.println("non esiste nessuno user " + nickname2);
			}
			
			
			//metodo funzionante: test superato
	    	
			
			
			/*
			 * METODO: modificaInformazioniUser
			 * 
			 * test: modifico la citta dello user franco da palermo a milano, lasciando inalterati gli altri attributi
			 
			boolean risultatoModifica;
			
			User userAggiornato = new User();
			String nickname = "franco";
			String password = "ciccio2";
			String email = "franco.com";
			String nome = "francesco";
			String cognome = "rizzo";
			String percorsoAvatar = "percorsoProva"; 
			String citta = "milano";
			String sesso = "M";
			int annoNascita = 1990; 
			
			userAggiornato.setNickname(nickname);
			userAggiornato.setPassword(password);
			userAggiornato.setEmail(email);
			userAggiornato.setNome(nome);
			userAggiornato.setCognome(cognome);
			userAggiornato.setAvatar(percorsoAvatar);
			userAggiornato.setCitta(citta);
			userAggiornato.setSesso(sesso);
			userAggiornato.setAnnoNascita(annoNascita);
			
			risultatoModifica = gestoreUserRemoto.modificaInformazioniUser(userAggiornato);
			
			if(risultatoModifica){
				System.out.println("informazioni modificate correttamente... la nuova città è: " + userAggiornato.getCitta());
			}
			else{
				System.out.println("ERRORE: informazioni non modificate");
			}
			
			//metodo funzionante
			*/
			
			/*
			 * METODO: confermaCancellazioneUser
			 * 
			 * test: elimino lo user franco
			 
			boolean risultatoEliminazione;
			String nickname = "franco";
			
			risultatoEliminazione = gestoreUserRemoto.confermaCancellazioneUser(nickname);
			
			// @@@@@@@@@@@@@@@ DA TESTARE @@@@@@@@@@@@@@@@@@@@
			 * 
			 * 
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