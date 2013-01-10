package swimv2EntityBean;

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
	
	@Column(name = "descrizione")
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
	 * Metodi costruttori e di creazione EnityBean dai Pojo
	 */
	
	
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
	 * codice SQL
	#tabella aiuto
	create table aiuto (
		codice int unsigned auto_increment primary key,
		descrizione text not null,
		user_richiedente varchar(15) not null,
		user_destinatario varchar(15) not null,
		codice_abilita smallint unsigned not null,
		momento_richiesta timestamp not null,
		momento_accettazione timestamp,
		foreign key(user_richiedente) references profilo(nickname),
		foreign key(user_destinatario) references profilo(nickname),
		foreign key(codice_abilita) references abilita(codice),
		foreign key(momento_richiesta) references data_completa(ts),
		foreign key(momento_accettazione) references data_completa(ts)
	);

	 */

}
