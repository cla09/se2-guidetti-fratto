package servlet;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.GestoreAbilitaRemote;
import session.GestoreProfiloRemote;
import session.GestoreUserRemote;
import utility.Comunicazione;
import utility.Messaggio;
import utility.TipoMessaggio;

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
			String email = (String) request.getAttribute("rEmail");
			Pattern pattern = Pattern.compile(Comunicazione.EMAIL_PATTERN);
			Matcher matcher = pattern.matcher(email);
			if(!matcher.find()) {
				Messaggio messaggio = new Messaggio(TipoMessaggio.ERRORE, Comunicazione.EMAIL_NON_VALIDA);
				// inoltrare il messaggio alla Home Page;
			}
			String nickname = (String) request.getAttribute("rNickname");
			if(!gestoreProfilo.controllaDisponibilitaNickname(nickname)) {
				Messaggio messaggio = new Messaggio(TipoMessaggio.AVVISO, Comunicazione.NICKNAME_NON_LIBERO);
				// inoltrare il messaggio alla Home Page;
			}
			GestoreUserRemote gestoreUser = (GestoreUserRemote) context.lookup("GestoreUser/remote");
			String nome = (String) request.getAttribute("rNome");
			String cognome = (String) request.getAttribute("rCognome");
			String sesso = (String) request.getAttribute("rSesso");
			int annoNascita = (Integer) request.getAttribute("rAnnoNascita");
			String citta = (String) request.getAttribute("rCitta");
			String password = (String) request.getAttribute("rPassword");
			String avatar = "";
			gestoreUser.registra(nickname, password, email, nome, cognome, avatar, citta, sesso, annoNascita);
			/* inltrare il nickname alla prossima pagina jsp */
			GestoreAbilitaRemote gestoreAbilita = (GestoreAbilitaRemote) context.lookup("GestoreAbilita/remote");
			/* recuperare le abilita' disponibili nel sistema */
			/* inoltrare tutti i parametri ricevuti alla prossima pagina jsp */
			/* inoltrare la lista delle abilita' recuperate alla prossima pagina jsp */
			/* chiamare la prossima pagina jsp */
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

}
