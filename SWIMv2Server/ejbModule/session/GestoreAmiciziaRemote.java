package session;

import javax.ejb.Remote;

@Remote
public interface GestoreAmiciziaRemote {
	
	boolean inviaRichiestaAmicizia(String nicknameRichiedente, String nicknameDestinatario);

}
