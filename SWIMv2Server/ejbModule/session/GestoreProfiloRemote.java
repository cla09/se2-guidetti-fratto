package session;

import javax.ejb.Remote;

import entity.Profilo;

@Remote
public interface GestoreProfiloRemote {

	boolean registra(String nickname, String password, String email, String nome, String cognome, String percorsoAvatar, String citta, String sesso, int annoNascita);
	
	Profilo recuperaDatiProfilo(String nickname);
}
