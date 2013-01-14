package servlet;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Abilita;
import session.GestoreAbilitaRemote;
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
			RequestDispatcher dispatcher;
			GestoreUserRemote gestoreUser = (GestoreUserRemote) context.lookup("GestoreUser/remote");
			String email = (String) request.getAttribute("rEmail");
			Pattern pattern = Pattern.compile(Comunicazione.EMAIL_PATTERN);
			Matcher matcher = pattern.matcher(email);
			if(!matcher.find()) {
				Messaggio messaggio = new Messaggio(TipoMessaggio.ERRORE, Comunicazione.EMAIL_NON_VALIDA);
				request.setAttribute("messaggio", messaggio);
				dispatcher = request.getRequestDispatcher("index.jsp");
				dispatcher.forward(request, response);
			}
			String nickname = (String) request.getAttribute("rNickname");
			if(!gestoreUser.controllaDisponibilitaNickname(nickname)) {
				Messaggio messaggio = new Messaggio(TipoMessaggio.AVVISO, Comunicazione.NICKNAME_NON_LIBERO);
				request.setAttribute("messaggio", messaggio);
				dispatcher = request.getRequestDispatcher("index.jsp");
				dispatcher.forward(request, response);
			}
			String nome = (String) request.getAttribute("rNome");
			String cognome = (String) request.getAttribute("rCognome");
			String sesso = (String) request.getAttribute("rSesso");
			int annoNascita = (Integer) request.getAttribute("rAnnoNascita");
			String citta = (String) request.getAttribute("rCitta");
			String password = (String) request.getAttribute("rPassword");
			String avatar = "";
			gestoreUser.registra(nickname, password, email, nome, cognome, avatar, citta, sesso, annoNascita);
			request.setAttribute("nickname", nickname);
			GestoreAbilitaRemote gestoreAbilita = (GestoreAbilitaRemote) context.lookup("GestoreAbilita/remote");
			List<Abilita> abilitaDisponibili = gestoreAbilita.recuperaAbilitaSistema();
			request.setAttribute("abilitaDisponibili", abilitaDisponibili);
			dispatcher = request.getRequestDispatcher("registrazione.jsp");
			dispatcher.forward(request, response);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

}
