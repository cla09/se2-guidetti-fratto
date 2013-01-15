package entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@DiscriminatorValue("user")
public class User extends Profilo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "citta")
	private String citta;
	
	@Column(name = "sesso")
	private String sesso;
	
	@Column(name = "anno_nascita")
	private int annoNascita;
	
	public User(){
		super();
	}
	
	public String getSesso() {
		return sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	public int getAnnoNascita() {
		return annoNascita;
	}

	public void setAnnoNascita(int annoNascita) {
		this.annoNascita = annoNascita;
	}
	
	public String getCitta() {
		return citta;
	}


	public void setCitta(String citta) {
		this.citta = citta;
	}
	
}
