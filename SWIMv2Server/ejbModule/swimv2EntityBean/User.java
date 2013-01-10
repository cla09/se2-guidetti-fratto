package swimv2EntityBean;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@DiscriminatorValue("No")
public class User extends Profilo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//User � proprietario della relazione con le abilit�
	@ManyToMany(
				cascade = CascadeType.PERSIST   //se elimino un utente le abilit� dichiarate restano
				)
	@JoinTable(									//dovrebbe andare cos�: nome della tabella di join e colonne di join
				name = "dichiarazione",	
				joinColumns = @JoinColumn(name = "user"),
				inverseJoinColumns = @JoinColumn(name = "codice_abilita")
				)
	private Set<Abilita> abilitaDichiarate;
	
	/*
	 * ***** da verificare il funzionamento del cascade: in questo momento � interpretato cos�: se cancello lo user succede ... *************
	 */
	
	//Lo user non � proprietario della relazione che � presente con le proposte di abilit�	
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
	 * Metodi costruttori e di creazione EnityBean dai Pojo
	 */
	
	
	/*
	 * *******************************
	 * Inizio metodi setter e getter *
	 *********************************
	 */	
	
	public Set<Abilita> getAbilitaDichiarate() {
		return abilitaDichiarate;
	}

	public void setAbilitaDichiarate(Set<Abilita> abilitaDichiarate) {
		this.abilitaDichiarate = abilitaDichiarate;
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
	
	
	
	
	
	
	
	

	//da continuare
	
}
