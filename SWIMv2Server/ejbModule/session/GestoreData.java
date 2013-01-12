package session;

import java.util.GregorianCalendar;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.ejb3.annotation.RemoteBinding;

import entity.Data;

/**
 * Session Bean implementation class GestoreData
 */
@Stateless
@RemoteBinding(jndiBinding = "GestoreDataJNDI")
public class GestoreData implements GestoreDataRemote {

	@PersistenceContext(unitName = "swimv2_unit")
	private EntityManager gestoreDB;
	
    /**
     * Default constructor. 
     */
    public GestoreData() {
        // TODO Auto-generated constructor stub
    }
    

    public Data creaData(){
    	GregorianCalendar dataAttuale = new GregorianCalendar();
    	long timestamp = dataAttuale.getTimeInMillis();
    	Data data;
    	
    	data = recuperaData(timestamp);
    	
   
    	if(data != null){
    		return data;
    	}
    	
    	data = new Data();
    	
    	data.setTimestamp( timestamp  );
    	data.setAnno( dataAttuale.get(GregorianCalendar.YEAR) );
    	data.setMese( dataAttuale.get(GregorianCalendar.MONTH) + 1 );
    	data.setGiorno( dataAttuale.get(GregorianCalendar.DATE) );
    	data.setOra( dataAttuale.get(GregorianCalendar.HOUR_OF_DAY) );
    	data.setMinuto( dataAttuale.get(GregorianCalendar.MINUTE) );
    	data.setSecondo( dataAttuale.get(GregorianCalendar.SECOND) );
    	
    	
    	gestoreDB.persist(data);
    	
    	System.out.println(timestamp);
    	return data;
    }

    
    public Data recuperaData(long timestamp){
    	
    	Data dataDaRecuperare;    	
    	Query q = gestoreDB.createQuery("SELECT d FROM Data d WHERE d.timestamp = :timestamp");
		q.setParameter("timestamp", timestamp);
		try{
			dataDaRecuperare = (Data) q.getSingleResult();
		}
		catch (NoResultException e) {
			return null;
		}
		
    	return dataDaRecuperare;
    }
}