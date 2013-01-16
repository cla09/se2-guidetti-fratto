package session;

import java.util.GregorianCalendar;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import entity.Data;

public class GestoreData {
	
	
	@PersistenceContext(unitName = "swimv2_unit")
	private EntityManager gestoreDB;

    public GestoreData() {
    	super();
    }
	
    //da verifica sto codice morto.........perch�?????????????
	public Data creaData(){
		
    	GregorianCalendar dataAttuale = new GregorianCalendar();
    	long timestamp = dataAttuale.getTimeInMillis();
    	System.out.println("timestamp: " + timestamp);

    	Data data = gestoreDB.find(Data.class, timestamp);
    	System.out.println("data recuperata " + data.getTimestamp());
    	
    	if(data == null){
    		System.out.println("creo una nuoca data");
    		data.setTimestamp( timestamp  );
    		data.setAnno( dataAttuale.get(GregorianCalendar.YEAR) );
    		data.setMese( dataAttuale.get(GregorianCalendar.MONTH) + 1 );
    		data.setGiorno( dataAttuale.get(GregorianCalendar.DATE) );
    		data.setOra( dataAttuale.get(GregorianCalendar.HOUR_OF_DAY) );
    		data.setMinuto( dataAttuale.get(GregorianCalendar.MINUTE) );
    		data.setSecondo( dataAttuale.get(GregorianCalendar.SECOND) );

    		try{
    			gestoreDB.persist(data);
    			return data;
    		}
    		catch (Exception e) {
    			System.out.println("errore inserimento nel DB");
    			return null;
    		}
    	}
    	else{
    		return data;
    	}
	}

}
