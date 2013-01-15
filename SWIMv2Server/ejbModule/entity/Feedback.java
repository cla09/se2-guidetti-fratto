package entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "feedback")
/*
 * con il gioco dell'id è probabile che non serva più
@GenericGenerator(name="aiutoPrimaryKey", 
					strategy="foreign",
					parameters={
								@Parameter(name="property", value="aiuto")
								}
				)
*/
public class Feedback implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
    
    @OneToOne
    private Aiuto aiuto;
            
	@Column(name = "valutazione_numerica", nullable = false)
	private int valutazioneNumericaFeedback;
	
	@Column(name = "valutazione_estesa", nullable = false)
	private String valutazioneEstesaFeedback;
	
	@ManyToOne(cascade = CascadeType.PERSIST)	//se elimino un feedback la data associata al momento di rilascio deve continuare ad esistere (ci potrebbe essere qualche altro evento con quella data)
	@JoinColumn(name = "momento_rilascio", referencedColumnName = "timestamp", nullable = false)
	private Data momentoRilascioFeedback;
	

	

	/*
	 * costruttore di default
	 */
	public Feedback(){
	}
	
	
	/*
	 * *******************************
	 * Inizio metodi setter e getter *
	 *********************************
	 */
	
	public Aiuto getAiutoAssociato() {
		return aiuto;
	}

	public void setAiutoAssociato(Aiuto aiuto) {
		this.aiuto = aiuto;
	}

	
	public int getValutazioneNumericaFeedback() {
		return valutazioneNumericaFeedback;
	}

	public void setValutazioneNumericaFeedback(short valutazioneNumericaFeedback) {
		this.valutazioneNumericaFeedback = valutazioneNumericaFeedback;
	}

	public String getValutazioneEstesaFeedback() {
		return valutazioneEstesaFeedback;
	}

	public void setValutazioneEstesaFeedback(String valutazioneEstesaFeedback) {
		this.valutazioneEstesaFeedback = valutazioneEstesaFeedback;
	}

	public Data getMomentoRilascioFeedback() {
		return momentoRilascioFeedback;
	}

	public void setMomentoRilascioFeedback(Data momentoRilascioFeedback) {
		this.momentoRilascioFeedback = momentoRilascioFeedback;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	/*
	 * *******************************
	 * Fine metodi setter e getter *
	 *********************************
	 */
	
	
/*
 * codice sql:
 * 
 * #tabella feedback
create table feedback (
	codice_aiuto int unsigned auto_increment primary key,
	valutazione_numerica tinyint(1) not null,
	valutazione_estesa text not null,
	momento_rilascio datetime not null,
	#se elimino (aggiorno) l'aiuto allora elimino (aggiorno) anche il feedback ad esso associato
	foreign key(codice_aiuto) references aiuto(codice)
		on delete cascade
		on update cascade,
	#non e' possibile eliminare (aggionare) una data associata al momento di rilascio di un feedback
	foreign key(momento_rilascio) references data_completa(ts)
		on delete restrict
		on update restrict
);

 */
	
}
