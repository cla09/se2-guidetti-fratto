package session;

import java.util.GregorianCalendar;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entity.Data;

@Stateless
public class GestoreData implements GestoreDataLocal {
	

	@PersistenceContext(unitName = "swimv2_unit")
	private EntityManager gestoreDB;

    public GestoreData() {
    	super();
    }

    @Override
    public Data creaData(){
    	GregorianCalendar dataAttuale = new GregorianCalendar();
    	long timestamp = dataAttuale.getTimeInMillis();
    	Data data = gestoreDB.find(Data.class, timestamp);
    	
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
    
}
