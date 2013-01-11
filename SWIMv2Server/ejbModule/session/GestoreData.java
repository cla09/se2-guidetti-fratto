package session;

import java.util.GregorianCalendar;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    
    
    public GregorianCalendar generaTimestamp(){
    	GregorianCalendar dataAttuale = new GregorianCalendar();
    	   	
    	return dataAttuale;
    }
    
    
    public boolean controllaEsistenzaTimestamp(long timestamp){
    	
    	
    	Data dataRecuperata = gestoreDB.find(Data.class, timestamp);
    	
    	if(dataRecuperata != null){
    		return true;
    	}
    	else{
    		return false;
    	}
    	
    }
    
    public Data creaData(){
    	
    	
    	/*
    	//genero il timestamp
    	GregorianCalendar dataAttuale = generaTimestamp();
    	
    	//oggetto data da restituire
    	Data data = new Data();
    	
    	//popolo i campi della struttura data
    	data.setTimestamp( dataAttuale.getTimeInMillis() );
    	data.setAnno( dataAttuale.get(GregorianCalendar.YEAR) );
		data.setMese( dataAttuale.get(GregorianCalendar.MONTH) + 1 ); //i mesi partono da 0
		data.setGiorno( dataAttuale.get(GregorianCalendar.DATE) );
		data.setOra( dataAttuale.get(GregorianCalendar.HOUR_OF_DAY) );
		data.setMinuto( dataAttuale.get(GregorianCalendar.MINUTE) );
		data.setSecondo( dataAttuale.get(GregorianCalendar.SECOND) );
		*/
    	
    	Data data = new Data (new GregorianCalendar());
		System.out.println("mill: "+ data.getTimestamp());
		
		
		
		//verifico se la data associata al timestamp generato è già presente nel DB
    	if( !( controllaEsistenzaTimestamp(data.getTimestamp())) ){
    		System.out.println("sono nell'if");
    		//se non esiste allora devo inserirla nel DB
    		gestoreDB.persist(data);
    	}
		
    	return data;
    	
    }

    public Data recuperaData(long timestamp){
    	
    	Data dataDaRecuperare;
    	dataDaRecuperare = gestoreDB.find(Data.class, timestamp);
    	
    	return dataDaRecuperare;
    }
}