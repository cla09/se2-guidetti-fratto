package entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "dichiarazione", uniqueConstraints = {@UniqueConstraint(columnNames = {"user", "id_abilita" } ) } )
public class Dichiarazione implements Serializable{

	
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "numero_feedback", nullable = false)
	private int numeroFeedback;
	
	@Column(name = "media_valutazioni", nullable = false)
	private int mediaValutazioni;
	
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "user", referencedColumnName = "nickname", nullable = false)
	private User userDichiarante;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_abilita", referencedColumnName = "id", nullable = false)
	private Abilita abilitaDichiarata;
	
	

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
