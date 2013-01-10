package swimv2EntityBean;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import commons.EnumStatoProposta;

@Entity
@Table(name = "proposta_abilita")
public class PropostaAbilita implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codice")
	private int id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "descrizione")
	private String descrizione;
	
	@Column(name = "stato_richiesta")
	private EnumStatoProposta statoProposta;
	
	//PropostaAbilita è proprietario della relazione con lo User
	@ManyToOne(cascade = CascadeType.PERSIST)		//se elimino proposta lo user associato non viene cancellato
	@JoinColumn(name = "user_proponente")
	private User userProponente;

	
	/*
	 * Metodi costruttori e di creazione EnityBean dai Pojo
	 */
	
	
	
	/*
	 * *******************************
	 * Inizio metodi setter e getter *
	 *********************************
	 */
	public int getIdPropostaAbilita() {
		return id;
	}

	public void setIdPropostaAbilita(int id) {
		this.id = id;
	}

	public String getNomePropostaAbilita() {
		return nome;
	}

	public void setNomePropostaAbilita(String nome) {
		this.nome = nome;
	}

	public String getDescrizionePropostaAbilita() {
		return descrizione;
	}

	public void setDescrizionePropostaAbilita(String descrizione) {
		this.descrizione = descrizione;
	}

	public EnumStatoProposta getStatoPropostaAbilita() {
		return statoProposta;
	}

	public void setStatoPropostaAbilita(EnumStatoProposta statoProposta) {
		this.statoProposta = statoProposta;
	}

	public User getUserProponenteAbilita() {
		return userProponente;
	}

	public void setUserProponenteAbilita(User userProponente) {
		this.userProponente = userProponente;
	}
	
	
	/*
	#tabella proposta_abilita
	create table proposta_abilita (
		codice int unsigned auto_increment primary key,
		nome varchar(15) not null,
		descrizione text not null,
		stato_richiesta enum('visionata', 'non visionata') default 'non visionata' not null,
		user_proponente varchar(15) not null,
		foreign key(user_proponente) references profilo(nickname)
	);
	*/
}
