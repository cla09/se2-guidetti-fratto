package entity;

import java.io.Serializable;
import java.util.GregorianCalendar;
import javax.persistence.*;


@Entity
@Table(name = "data_completa")
public class Data implements Serializable{

	private static final long serialVersionUID = 1L;	

	@Id
	@Column(name = "timestamp")
	private long timestamp;	
	
	@Column(name = "anno", nullable = false)
	private int anno;
	
	@Column(name = "mese", nullable = false)
	private int mese;
	
	@Column(name = "giorno", nullable = false)
	private int giorno;
	
	@Column(name = "ora", nullable = false)
	private int ora;
	
	@Column(name = "minuto", nullable = false)
	private int minuto;
	
	@Column(name = "secondo", nullable = false)
	private int secondo;

	/*
	 * costruttore di default
	 */
	public Data(){
		
	}
	
	public Data(GregorianCalendar dataAttuale){
		this.timestamp = dataAttuale.getTimeInMillis();
		this.anno =  dataAttuale.get(GregorianCalendar.YEAR) ;
		this.mese = GregorianCalendar.MONTH + 1 ; //i mesi partono da 0
		this.giorno = dataAttuale.get(GregorianCalendar.DATE) ;
		this.ora = dataAttuale.get(GregorianCalendar.HOUR_OF_DAY) ;
		this.minuto =  dataAttuale.get(GregorianCalendar.MINUTE );
		this.secondo = dataAttuale.get(GregorianCalendar.SECOND);
	}
	/*
	 * *******************************
	 * Inizio metodi setter e getter *
	 *********************************
	 */
	
	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	public int getMese() {
		return mese;
	}

	public void setMese(int mese) {
		this.mese = mese;
	}

	public int getGiorno() {
		return giorno;
	}

	public void setGiorno(int giorno) {
		this.giorno = giorno;
	}

	public int getOra() {
		return ora;
	}

	public void setOra(int ora) {
		this.ora = ora;
	}

	public int getMinuto() {
		return minuto;
	}

	public void setMinuto(int minuto) {
		this.minuto = minuto;
	}

	public int getSecondo() {
		return secondo;
	}

	public void setSecondo(int secondo) {
		this.secondo = secondo;
	}

	/*
	public int getIdData() {
		return idData;
	}

	public void setIdData(int idData) {
		this.idData = idData;
	}
	*/
	
	/*
	 * *******************************
	 * Fine metodi setter e getter *
	 *********************************
	 */
	
	
	
	
/*
 * codice sql:
 * 
 * #tabella data
create table data_completa (
	ts datetime primary key, #timestamp
	anno smallint(4) not null,
	mese smallint(2) unsigned not null,
	giorno smallint(2) unsigned not null,
	ora smallint(2) unsigned not null,
	minuto smallint(2) unsigned not null,
	secondo smallint(2) unsigned not null
);	
 */

}
