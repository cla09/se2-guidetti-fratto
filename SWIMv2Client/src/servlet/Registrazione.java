package servlet;

import java.io.IOException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.GestoreAbilitaRemote;
import session.GestoreProfiloRemote;

public class Registrazione extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
    public Registrazione() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Context context = new InitialContext();
			GestoreProfiloRemote gestoreProfilo = (GestoreProfiloRemote) context.lookup("GestoreProfilo/remote");
			/* eseguire un controllo sull'indirizzo email */
			/* if( !(controllo disponibilità nickname) ) {
				tornare alla Home Page con messaggio di nickname non disponibile
			} */
			GestoreAbilitaRemote gestoreAbilita = (GestoreAbilitaRemote) context.lookup("GestoreAbilita/remote");
			/* recuperare le abilità disponibili nel sistema */
			/* inoltrare tutti i parametri ricevuti alla prossima pagina jsp */
			/* inoltrare la lista delle abilità recuperate alla prossima pagina jsp */
			/* chiamare la prossima pagina jsp */
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

}
