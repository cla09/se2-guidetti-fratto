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
	
    //da verifica sto codice morto.........perchè?????????????
	public Data creaData(){
		Data dataDaCreare;
    	GregorianCalendar dataAttuale = new GregorianCalendar();
    	long timestamp = dataAttuale.getTimeInMillis();
    	System.out.println("timestamp: " + timestamp);

    	dataDaCreare = gestoreDB.find(Data.class, timestamp);
    	System.out.println("data recuperata " + dataDaCreare.getTimestamp());
    	
    	if(dataDaCreare == null){
    		System.out.println("creo una nuoca data");
    		dataDaCreare.setTimestamp( timestamp  );
    		dataDaCreare.setAnno( dataAttuale.get(GregorianCalendar.YEAR) );
    		dataDaCreare.setMese( dataAttuale.get(GregorianCalendar.MONTH) + 1 );
    		dataDaCreare.setGiorno( dataAttuale.get(GregorianCalendar.DATE) );
    		dataDaCreare.setOra( dataAttuale.get(GregorianCalendar.HOUR_OF_DAY) );
    		dataDaCreare.setMinuto( dataAttuale.get(GregorianCalendar.MINUTE) );
    		dataDaCreare.setSecondo( dataAttuale.get(GregorianCalendar.SECOND) );

    		try{
    			gestoreDB.persist(dataDaCreare);
    			return dataDaCreare;
    		}
    		catch (Exception e) {
    			System.out.println("errore inserimento nel DB");
    			return null;
    		}
    	}
    	else{
    		return dataDaCreare;
    	}
	}

}
