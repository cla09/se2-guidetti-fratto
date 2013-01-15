package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import entity.User;
import session.GestoreDichiarazioneRemote;
//import session.GestoreUserRemote;
import utility.Comunicazione;
import utility.Messaggio;
import utility.TipoMessaggio;

public class CompletamentoRegistrazione extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public CompletamentoRegistrazione() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			RequestDispatcher dispatcher;
			Context context = new InitialContext();
			//GestoreUserRemote gestoreUser = (GestoreUserRemote) context.lookup("GestoreUserJNDI");
			GestoreDichiarazioneRemote gestoreDichiarazione = (GestoreDichiarazioneRemote) context.lookup("GestoreDichiarazioneJNDI");
			String nickname = (String) request.getAttribute("nickname");
			String[] abilitaScelte = request.getParameterValues("abilitaScelte");
			List<Integer> idAbilita = new ArrayList<Integer>();
			Messaggio messaggio = new Messaggio(TipoMessaggio.CONFERMA, Comunicazione.REGISTRAZIONE_COMPLETATA);
			request.setAttribute("messaggio", messaggio);
			if(abilitaScelte != null) {
				//l'utente ha scelta almeno un'abilita
				for(int i = 0; i < abilitaScelte.length; i++) {
					try {
						System.out.println(abilitaScelte[i]);
						int id = Integer.parseInt(abilitaScelte[i]);
						idAbilita.add(id);
					} catch (NumberFormatException numberFormatE) {
						numberFormatE.printStackTrace();
					}
				}
				gestoreDichiarazione.setAbilitaDichiarate(nickname, idAbilita);
			}
			dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		} catch (NamingException namingE) {
			namingE.printStackTrace();
		}
	}

}
