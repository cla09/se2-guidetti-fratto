package entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "feedback")
/*
 * con il gioco dell'id � probabile che non serva pi�
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
	@Column(name = "id")
	private long id;
	
	/*
	@Id
	@GeneratedValue(generator = "aiutoPrimaryKey")
	*/
	@Column(name = "codice_aiuto", unique = true)
    private int codiceAiuto;
    
    @OneToOne(mappedBy = "feedback")	
    @PrimaryKeyJoinColumn(name = "codice_aiuto")
    private Aiuto aiuto;
            
	@Column(name = "valutazione_numerica", nullable = false)
	private short valutazioneNumericaFeedback;
	
	@Column(name = "valutazione_estesa", nullable = false)
	private String valutazioneEstesaFeedback;
	
	@ManyToOne(cascade = CascadeType.PERSIST)	//se elimino un feedback la data associata al momento di rilascio deve continuare ad esistere (ci potrebbe essere qualche altro evento con quella data)
	@JoinColumn(name = "momento_rilascio")
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
	
	public int getCodiceFeedback() {
		return codiceAiuto;
	}

	public void setCodiceFeedback(int codiceFeedback) {
		this.codiceAiuto = codiceFeedback;
	}

	public Aiuto getAiutoAssociato() {
		return aiuto;
	}

	public void setAiutoAssociato(Aiuto aiuto) {
		this.aiuto = aiuto;
	}

	
	public short getValutazioneNumericaFeedback() {
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


	public long getId() {
		return id;
	}


	public void setId(long id) {
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
