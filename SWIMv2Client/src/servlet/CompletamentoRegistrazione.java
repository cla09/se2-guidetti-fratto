package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import session.GestoreDichiarazione;
import session.GestoreDichiarazioneRemote;
import session.GestoreUserRemote;

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
			Context context = new InitialContext();
			GestoreUserRemote gestoreUser = (GestoreUserRemote) context.lookup("GestoreUserJNDI");
			GestoreDichiarazioneRemote gestoreDichiarazione = (GestoreDichiarazioneRemote) context.lookupLink("GestoreDichiarazioneJNDI");
			String nickname = (String) request.getAttribute("nickname");
			int numeroAbilità = (Integer) request.getAttribute("numeroAbilita");
			List<Integer> idAbilita = new ArrayList<Integer>();
			for(int i = 0; i < numeroAbilità; i++) {
				if(request.getParameter("abilitaScelta" + i) != null) {
					try {
						Integer id = Integer.parseInt(request.getParameter("abilitaScelta" + i));
						idAbilita.add(id);
					} catch (NumberFormatException numberFormatE) {
						numberFormatE.printStackTrace();
					}
				}
			}
			
		} catch (NamingException namingE) {
			namingE.printStackTrace();
		}
	}

}
