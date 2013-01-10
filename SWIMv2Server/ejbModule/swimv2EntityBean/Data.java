package swimv2EntityBean;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

@Entity
@Table(name = "data_completa")
public class Data implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ts")
	private Timestamp timestampData;
	
	@Column(name = "anno")
	private int annoData;
	
	@Column(name = "mese")
	private int meseData;
	
	@Column(name = "giorno")
	private int giornoData;
	
	@Column(name = "ora")
	private int oraData;
	
	@Column(name = "minuto")
	private int minutoData;
	
	@Column(name = "secondo")
	private int secondoData;

	/*
	 * Metodi costruttori e di creazione EnityBean dai Pojo
	 */
	
	/*
	 * *******************************
	 * Inizio metodi setter e getter *
	 *********************************
	 */
	
	public Timestamp getTimestampData() {
		return timestampData;
	}

	public void setTimestampData(Timestamp timestampData) {
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
	 * codice SQL
	 * 
	create table data_completa (
		ts timestamp primary key, #timestamp
		anno year not null,
		mese smallint(2) unsigned not null,
		giorno smallint(2) unsigned not null,
		ora smallint(2) unsigned not null,
		minuto smallint(2) unsigned not null,
		secondo smallint(2) unsigned not null
	);

	 */
}
