package entity;

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
import javax.persistence.UniqueConstraint;


@Entity
@Table(name = "dichiarazione", uniqueConstraints = {@UniqueConstraint(columnNames = {"user", "codice_abilita" } ) } )
public class Dichiarazione implements Serializable{

	
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "user", nullable = false)
	private User userDichiarante;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "codice_abilita", nullable = false)
	private Abilita abilitaDichiarata;
	
	@Column(name = "numero_feedback")
	private int numeroFeedback;
	
	@Column(name = "media_valutazioni")
	private int mediaValutazioni;

	public int getIdDichiarazione() {
		return id;
	}

	public void setIdDichiarazione(int id) {
		this.id = id;
	}

	public User getUserDichiarante() {
		return userDichiarante;
	}

	public void setUserDichiarante(User userDichiarante) {
		this.userDichiarante = userDichiarante;
	}

	public Abilita getAbilitaDichiarata() {
		return abilitaDichiarata;
	}

	public void setAbilitaDichiarata(Abilita abilitaDichiarata) {
		this.abilitaDichiarata = abilitaDichiarata;
	}

	public int getNumeroFeedback() {
		return numeroFeedback;
	}

	public void setNumeroFeedback(int numeroFeedback) {
		this.numeroFeedback = numeroFeedback;
	}

	public int getMediaValutazioni() {
		return mediaValutazioni;
	}

	public void setMediaValutazioni(int mediaValutazioni) {
		this.mediaValutazioni = mediaValutazioni;
	}
	
}
