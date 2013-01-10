package swimv2EntityBean;

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
@GenericGenerator(name="aiutoPrimaryKey", 
					strategy="foreign",
					parameters={
								@Parameter(name="property", value="aiuto")
								}
				)
public class Feedback implements Serializable{

	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(generator = "aiutoPrimaryKey")
	@Column(name = "codice_aiuto")
    private int codiceFeedback;
    
    @OneToOne(mappedBy = "feedback")	
    @PrimaryKeyJoinColumn(name = "codice_aiuto")
    private Aiuto aiuto;
        
	@Column(name = "valutazione_numerica")
	private short valutazioneNumericaFeedback;
	
	@Column(name = "valutazione_estesa")
	private String valutazioneEstesaFeedback;
	
	@ManyToOne(cascade = CascadeType.PERSIST)	//se elimino un feedback la data associata al momento di rilascio deve continuare ad esistere (ci potrebbe essere qualche altro evento con quella data)
	@JoinColumn(name = "momento_rilascio")
	private Data momentoRilascioFeedback;

	
	/*
	 * Metodi costruttori e di creazione EnityBean dai Pojo
	 */
	
	
	
	/*
	 * *******************************
	 * Inizio metodi setter e getter *
	 *********************************
	 */
	
	public int getCodiceFeedback() {
		return codiceFeedback;
	}

	public void setCodiceFeedback(int codiceFeedback) {
		this.codiceFeedback = codiceFeedback;
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
	
	
	
	
	/*
	#tabella feedback
	create table feedback (
		codice_aiuto int unsigned auto_increment primary key,
		valutazione_numerica tinyint(1) not null,
		valutazione_estesa text not null,
		momento_rilascio timestamp not null,
		foreign key(codice_aiuto) references aiuto(codice),
		foreign key(momento_rilascio) references data_completa(ts)
	);
	 */
	
	
}
