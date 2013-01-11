package session;

import javax.ejb.Remote;

@Remote
public interface GestoreAmiciziaRemote {
	
	void inserisciRichiestaAmicizia(String nicknameRichiedente, String nicknameDestinatario);

}
