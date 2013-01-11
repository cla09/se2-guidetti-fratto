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
	
	@OneToMany(mappedBy = "userDichiarante", cascade = CascadeType.REMOVE)
	private List<Dichiarazione> dichiarazioni;
	
	/*
	 * ***** da verificare il funzionamento del cascade: in questo momento è interpretato così: se cancello lo user succede ... *************
	 */
	
	//Lo user non è proprietario della relazione che è presente con le proposte di abilità	
	@OneToMany(mappedBy = "userProponente", cascade = CascadeType.REMOVE)  //se cancello lo User elimino anche tutte le proposta ad esso associate
	private List<PropostaAbilita> proposteAbilita;
	
	@OneToMany(mappedBy = "userRichiedenteAmicizia", cascade = CascadeType.REMOVE)
	private List<Amicizia> amicizieRichieste;
	
	@OneToMany(mappedBy = "userDestinatarioAmicizia", cascade = CascadeType.REMOVE)
	private List<Amicizia> amicizieRicevute;
	
	@OneToMany(mappedBy = "userRichiedenteAiuto", cascade = CascadeType.REMOVE)
	private List<Aiuto> richiesteAiutoAvanzate;
	
	@OneToMany(mappedBy = "userDestinatarioAiuto", cascade = CascadeType.REMOVE)
	private List<Aiuto> richiesteAiutoRicevute;

	/*
	 * metodo costruttore
	 */
	public User(){
	}
	
	
	/*
	 * *******************************
	 * Inizio metodi setter e getter *
	 *********************************
	 */	
	
	public List<Dichiarazione> getAbilitaDichiarate() {
		return dichiarazioni;
	}

	public void setDichiarazioni(List<Dichiarazione> dichiarazioni) {
		this.dichiarazioni = dichiarazioni;
	}

	public List<PropostaAbilita> getProposteAbilita() {
		return proposteAbilita;
	}

	public void setProposteAbilita(List<PropostaAbilita> proposteAbilita) {
		this.proposteAbilita = proposteAbilita;
	}

	public List<Amicizia> getAmicizieRichieste() {
		return amicizieRichieste;
	}

	public void setAmicizieRichieste(List<Amicizia> amicizieRichieste) {
		this.amicizieRichieste = amicizieRichieste;
	}

	public List<Amicizia> getAmicizieRicevute() {
		return amicizieRicevute;
	}

	public void setAmicizieRicevute(List<Amicizia> amicizieRicevute) {
		this.amicizieRicevute = amicizieRicevute;
	}

	public List<Aiuto> getRichiesteAiutoAvanzate() {
		return richiesteAiutoAvanzate;
	}

	public void setRichiesteAiutoAvanzate(List<Aiuto> richiesteAiutoAvanzate) {
		this.richiesteAiutoAvanzate = richiesteAiutoAvanzate;
	}

	public List<Aiuto> getRichiesteAiutoRicevute() {
		return richiesteAiutoRicevute;
	}

	public void setRichiesteAiutoRicevute(List<Aiuto> richiesteAiutoRicevute) {
		this.richiesteAiutoRicevute = richiesteAiutoRicevute;
	}
	
	
	/*
	 * *******************************
	 * Inizio metodi setter e getter *
	 *********************************
	 */	
	
	
	
}
