package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
@DiscriminatorValue("user")
public class User extends Profilo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/*
	//User è proprietario della relazione con le abilità
	@ManyToMany(
				cascade = CascadeType.PERSIST   //se elimino un utente le abilità dichiarate restano
				)
	@JoinTable(									//dovrebbe andare così: nome della tabella di join e colonne di join
				name = "dichiarazione",	
				joinColumns = @JoinColumn(name = "user"),
				inverseJoinColumns = @JoinColumn(name = "codice_abilita")
				)
	private Set<Abilita> abilitaDichiarate;
	*/
	
	@Column(name = "citta")
	private String citta;
	
	@Column(name = "sesso")
	private String sesso;
	
	@Column(name = "anno_nascita")
	private int annoNascita;
	
	@OneToMany(mappedBy = "userDichiarante", cascade = CascadeType.ALL)
	private List<Dichiarazione> dichiarazioni;

	/*
	 * metodo costruttore
	 */
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


	public List<Dichiarazione> getDichiarazioni() {
		return dichiarazioni;
	}


	public void setDichiarazioni(List<Dichiarazione> dichiarazioni) {
		this.dichiarazioni = dichiarazioni;
	}
	
	/*
	 * *******************************
	 * Inizio metodi setter e getter *
	 *********************************
	 */	
	
	
	
}
