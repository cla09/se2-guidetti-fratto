package entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name= "aiuto")
public class Aiuto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codice")
	private int codiceAiuto;
	
	@Column(name = "descrizione", nullable = false)
	private String descrizioneAiuto;	
	
	@ManyToOne(cascade = CascadeType.PERSIST)	//se elimino un aiuto lo user richiedente deve continuare ad esistere
	@JoinColumn(name = "user_richiedente", nullable = false)
	private User userRichiedenteAiuto;
	
	@ManyToOne(cascade = CascadeType.PERSIST)	//se elimino un aiuto lo user destinatario deve continuare ad esistere
	@JoinColumn(name = "user_destinatario", nullable = false)
	private User userDestinatarioAiuto;
	
	@ManyToOne(cascade = CascadeType.PERSIST)	//se elimino un aiuto l'abilità associata deve continuare ad esistere
	@JoinColumn(name = "codice_abilita", nullable = false)
	private Abilita abilitaAiuto;
	
	@ManyToOne(cascade = CascadeType.PERSIST)	//se elimino un aiuto la data associata al momento di richiesta deve continuare ad esistere (ci potrebbe essere qualche altro evento con quella data)
	@JoinColumn(name = "momento_richiesta", nullable = false)
	private Data momentoRichiestaAiuto;

	@ManyToOne(cascade = CascadeType.PERSIST)	//se elimino un aiuto la data associata al momento di accettazione deve continuare ad esistere (ci potrebbe essere qualche altro evento con quella data)
	@JoinColumn(name = "momento_accettazione")
	private Data momentoAccettazioneAiuto;

	
	@OneToOne(cascade = CascadeType.REMOVE)		//se elimino un aiuto il suo feedback deve essere eliminato
	private Feedback feedback;
	
	
	/*
	 * costruttore di default
	 */
	public Aiuto(){
	}
	
	/*
	 * *******************************
	 * Inizio metodi setter e getter *
	 *********************************
	 */
	
	public int getCodiceAiuto() {
		return codiceAiuto;
	}

	public void setCodiceAiuto(int codiceAiuto) {
		this.codiceAiuto = codiceAiuto;
	}

	public String getDescrizioneAiuto() {
		return descrizioneAiuto;
	}

	public void setDescrizioneAiuto(String descrizioneAiuto) {
		this.descrizioneAiuto = descrizioneAiuto;
	}

	public User getUserRichiedenteAiuto() {
		return userRichiedenteAiuto;
	}

	public void setUserRichiedenteAiuto(User userRichiedenteAiuto) {
		this.userRichiedenteAiuto = userRichiedenteAiuto;
	}

	public User getUserDestinatarioAiuto() {
		return userDestinatarioAiuto;
	}

	public void setUserDestinatarioAiuto(User userDestinatarioAiuto) {
		this.userDestinatarioAiuto = userDestinatarioAiuto;
	}

	public Abilita getAbilitaAiuto() {
		return abilitaAiuto;
	}

	public void setAbilitaAiuto(Abilita abilitaAiuto) {
		this.abilitaAiuto = abilitaAiuto;
	}

	public Data getMomentoRichiestaAiuto() {
		return momentoRichiestaAiuto;
	}

	public void setMomentoRichiestaAiuto(Data momentoRichiestaAiuto) {
		this.momentoRichiestaAiuto = momentoRichiestaAiuto;
	}

	public Data getMomentoAccettazioneAiuto() {
		return momentoAccettazioneAiuto;
	}

	public void setMomentoAccettazioneAiuto(Data momentoAccettazioneAiuto) {
		this.momentoAccettazioneAiuto = momentoAccettazioneAiuto;
	}
	
	
	public Feedback getFeedbackAiuto() {
		return feedback;
	}

	public void setFeedbackAiuto(Feedback feedback) {
		this.feedback = feedback;
	}
	
	
	/*
	 * *******************************
	 * Fine metodi setter e getter *
	 *********************************
	 */
	
	/*
	 * codice sql:
	 * 
	 * create table aiuto (
	codice int unsigned auto_increment,
	descrizione varchar(140) not null,
	user_richiedente varchar(20) not null,
	user_destinatario varchar(20) not null,
	codice_abilita int unsigned not null,
	momento_richiesta bigint unsigned not null,
	momento_accettazione bigint unsigned,
	primary key (codice),
	#se elimino (aggiorno) lo user richiedente allora tutte le sue richieste di aiuto devono essere eliminate(aggiornate)
	foreign key(user_richiedente) references profilo(nickname)
		on delete cascade
		on update cascade,
	#se elimino (aggiorno) lo user destiatario allora tutte le sue richieste di aiuto devono essere eliminate(aggiornate)
	foreign key(user_destinatario) references profilo(nickname)
		on delete cascade
		on update cascade,
	#non e' possibile eliminare (aggiornare) l'abilita oggetto di una richiesta di aiuto
	foreign key(codice_abilita) references abilita(codice)
		on delete restrict
		on update restrict,
	#non e' possibile eliminare (aggiornare) una data associata al momento di richiesta di un aiuto
	foreign key(momento_richiesta) references data_completa(timestamp)
		on delete restrict
		on update restrict,
	#non e' possibile eliminare (aggiornare) una data associata al momento di accettazione di un aiuto
	foreign key(momento_accettazione) references data_completa(timestamp)
		on delete restrict
		on update restrict
);

	 */
}
