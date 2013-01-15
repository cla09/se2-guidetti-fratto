package entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "Abilita")
public class Abilita implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int idAbilita;
	
	@Column(name = "nome", nullable = false)
	private String nomeAbilita;
	
	@Column(name = "icona")
	private String percorsoIcona;		//da sistemare in modo tale da fare l'upload dell'immagine ed il recuper
	
	@Column(name = "descrizione", nullable = false)
	private String descrizioneAbilita;
	
	/*
	@ManyToMany(mappedBy = "abilitaDichiarate")
	private List<User> utentiDichiaranti;
	*/
	
	/*
	@OneToMany(mappedBy = "abilitaDichiarata", cascade = CascadeType.REMOVE)
	private List<Dichiarazione> dichiarazioni;
	*/
	/*
	 * costruttore di default
	 */
	public Abilita(){	
	}
	
	/*
	 * *******************************
	 * Inizio metodi setter e getter *
	 *********************************
	 */
	
	public int getCodice() {
		return idAbilita;
	}

	public void setCodice(int codiceAbilita) {
		this.idAbilita = codiceAbilita;
	}

	public String getNome() {
		return nomeAbilita;
	}

	public void setNome(String nomeAbilita) {
		this.nomeAbilita = nomeAbilita;
	}

	public String getIcona() {
		return percorsoIcona;
	}

	public void setIcona(String percorsoIcona) {
		this.percorsoIcona = percorsoIcona;
	}

	public String getDescrizioneAbilita() {
		return descrizioneAbilita;
	}

	public void setDescrizioneAbilita(String descrizioneAbilita) {
		this.descrizioneAbilita = descrizioneAbilita;
	}
	
	/*
	public List<User> getUtentiDichiaranti() {
		return utentiDichiaranti;
	}

	public void setUtentiDichiaranti(List<User> utentiDichiaranti) {
		this.utentiDichiaranti = utentiDichiaranti;
	}
	*/
	
	/*
	public List<Dichiarazione> getDichiarazioni() {
		return dichiarazioni;
	}

	public void setDichiarazioni(List<Dichiarazione> dichiarazioni) {
		this.dichiarazioni = dichiarazioni;
	}
	*/
	
	/*
	 * *******************************
	 * Fine metodi setter e getter *
	 *********************************
	 */
	
/*codice sql:
 * 
 * 
 * #tabella abilita
create table abilita (
	codice smallint unsigned auto_increment primary key,
	nome varchar(15) not null, 
	icona blob,
	descrizione text not null
);

 */
	
}


