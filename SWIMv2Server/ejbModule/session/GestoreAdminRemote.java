package session;

import javax.ejb.Remote;

import entity.Admin;

@Remote
public interface GestoreAdminRemote {

	/**
	 * Il metodo serve per recuperare il profilo associato all'utente amministratore
	 * 
	 * @return
	 */
	public Admin getAdmin();
	
}
