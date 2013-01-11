package entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "data_completa")
public class Data implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "timestamp")
	private String timestampData;	//bisogna vedere se il tipo String va bene
	
	@Column(name = "anno", nullable = false)
	private int annoData;
	
	@Column(name = "mese", nullable = false)
	private int meseData;
	
	@Column(name = "giorno", nullable = false)
	private int giornoData;
	
	@Column(name = "ora", nullable = false)
	private int oraData;
	
	@Column(name = "minuto", nullable = false)
	private int minutoData;
	
	@Column(name = "secondo", nullable = false)
	private int secondoData;

	/*
	 * costruttore di default
	 */
	public Data(){
	}
	
	/*
	 * *******************************
	 * Inizio metodi setter e getter *
	 *********************************
	 */
	
	public String getTimestampData() {
		return timestampData;
	}

	public void setTimestampData(String timestampData) {
		this.timestampData = timestampData;
	}

	public int getAnnoData() {
		return annoData;
	}

	public void setAnnoData(int annoData) {
		this.annoData = annoData;
	}

	public int getMeseData() {
		return meseData;
	}

	public void setMeseData(int meseData) {
		this.meseData = meseData;
	}

	public int getGiornoData() {
		return giornoData;
	}

	public void setGiornoData(int giornoData) {
		this.giornoData = giornoData;
	}

	public int getOraData() {
		return oraData;
	}

	public void setOraData(int oraData) {
		this.oraData = oraData;
	}

	public int getMinutoData() {
		return minutoData;
	}

	public void setMinutoData(int minutoData) {
		this.minutoData = minutoData;
	}

	public int getSecondoData() {
		return secondoData;
	}

	public void setSecondoData(int secondoData) {
		this.secondoData = secondoData;
	}
	
	/*
	 * *******************************
	 * Fine metodi setter e getter *
	 *********************************
	 */
	
	
	
	//override equals e compareTo
	
	
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
