package session;

import java.util.GregorianCalendar;

import javax.ejb.Remote;

import entity.Data;

@Remote
public interface GestoreDataRemote {
	
	public GregorianCalendar generaTimestamp();
    
    public boolean controllaEsistenzaTimestamp(long timestamp);
    
    public Data creaData();

    public Data recuperaData(long timestamp);
}