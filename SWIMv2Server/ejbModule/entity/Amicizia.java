package entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "amicizia")
public class Amicizia implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codice")
	private int codiceAmicizia;
	
	@ManyToOne(cascade = CascadeType.PERSIST)	//se elimino un'amicizia lo user richiedente deve continuare ad esistere
	@JoinColumn(name = "user_richiedente", nullable = false)
	private User userRichiedenteAmicizia;
	
	@ManyToOne(cascade = CascadeType.PERSIST)	//se elimino un'amicizia lo user destinatario deve continuare ad esistere
	@JoinColumn(name = "user_destinatario", nullable = false)
	private User userDestinatarioAmicizia;
	
	@ManyToOne(cascade = CascadeType.PERSIST)	//se elimino un'amicizia la data associata al momento di richiesta deve continuare ad esistere (ci potrebbe essere qualche altro evento con quella data)
	@JoinColumn(name = "momento_richiesta", nullable = false)
	private Data momentoRichiestaAmicizia;
	
	@ManyToOne(cascade = CascadeType.PERSIST)	//se elimino un'amicizia la data associata al momento di accettazione deve continuare ad esistere (ci potrebbe essere qualche altro evento con quella data)
	@JoinColumn(name = "momento_accettazione")
	private Data momentoAccettazioneAmicizia;
	
	
	/*
	 * costruttore di default
	 */
	public Amicizia(){
	}
	
	
	/*
	 * *******************************
	 * Inizio metodi setter e getter *
	 *********************************
	 */
	
	public int getCodiceAmicizia() {
		return codiceAmicizia;
	}

	public void setCodiceAmicizia(int codiceAmicizia) {
		this.codiceAmicizia = codiceAmicizia;
	}

	public User getUserRichiedenteAmicizia() {
		return userRichiedenteAmicizia;
	}

	public void setUserRichiedenteAmiciza(User userRichiedenteAmicizia) {
		this.userRichiedenteAmicizia = userRichiedenteAmicizia;
	}

	public User getUserDestinatarioAmicizia() {
		return userDestinatarioAmicizia;
	}

	public void setUserDestinatarioAmicizia(User userDestinatarioAmicizia) {
		this.userDestinatarioAmicizia = userDestinatarioAmicizia;
	}

	public Data getMomentoRichiestaAmicizia() {
		return momentoRichiestaAmicizia;
	}

	public void setMomentoRichiestaAmicizia(Data momentoRichiestaAmicizia) {
		this.momentoRichiestaAmicizia = momentoRichiestaAmicizia;
	}

	public Data getMomentoAccettazioneAmicizia() {
		return momentoAccettazioneAmicizia;
	}

	public void setMomentoAccettazioneAmicizia(
			Data momentoAccettazioneAmicizia) {
		this.momentoAccettazioneAmicizia = momentoAccettazioneAmicizia;
	}
	
	/*
	 * *******************************
	 * Fine metodi setter e getter *
	 *********************************
	 */
	
	
/*
 * codice SQL
 * 
 * codice int unsigned auto_increment primary key,
	user_richiedente varchar(15) not null,
	user_destinatario varchar(15) not null,
	momento_richiesta datetime not null,
	momento_accettazione datetime,
	#se elimino (aggiorno) lo user richiedente allora tutte le sue richieste di amicizia devono essere eliminate(aggiornate)
	foreign key(user_richiedente) references profilo(nickname)
		on delete cascade
		on update cascade,
	#se elimino (aggiorno) lo user destiatario allora tutte le sue richieste di amicizia devono essere eliminate(aggiornate)
	foreign key(user_destinatario) references profilo(nickname)
		on delete cascade
		on update cascade,
	#non e' possibile eliminare (aggiornare) una data associata al momento di richiesta di un'amicizia
	foreign key(momento_richiesta) references data_completa(ts)
		on delete restrict
		on update restrict,
	#non e' possibile eliminare (aggiornare) una data associata al momento di accettazione di un'amicizia
	foreign key(momento_accettazione) references data_completa(ts)
		on delete restrict
		on update restrict
);	
 */
}
