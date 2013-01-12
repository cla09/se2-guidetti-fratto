package session;

import javax.ejb.Remote;

import entity.Data;

@Remote
public interface GestoreDataRemote {
	
	public Data creaData();

    public Data recuperaData(long timestamp);
}