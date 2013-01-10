package swimv2EntityBean;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Abilita")
public class Abilita implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codice")
	private int codiceAbilita;
	
	@Column(name = "nome")
	private String nomeAbilita;
	
	@Lob
	@Column(name = "icona")
	private File icona;
	
	@Column(name = "descrizione")
	private String descrizioneAbilita;
	
	@ManyToMany(mappedBy = "abilitaDichiarate")
	private List<User> utentiDichiaranti;

	/*
	 * Metodi costruttori e di creazione EnityBean dai Pojo
	 */
	/*
	public Abilita(String nomeAbilita, String percorsoIconaAbilita, String descrizioneAbilita){
		this.nomeAbilita = nomeAbilita;
		this.percorsoIcona = percorsoIconaAbilita;
		this.descrizioneAbilita = descrizioneAbilita;
	}
	*/
	public Abilita(){
		
	}
	
	/*
	 * *******************************
	 * Inizio metodi setter e getter *
	 *********************************
	 */
	
	public int getCodiceAbilita() {
		return codiceAbilita;
	}

	public void setCodiceAbilita(int codiceAbilita) {
		this.codiceAbilita = codiceAbilita;
	}

	public String getNomeAbilita() {
		return nomeAbilita;
	}

	public void setNomeAbilita(String nomeAbilita) {
		this.nomeAbilita = nomeAbilita;
	}

	public File getIcona() {
		return icona;
	}

	public void setIcona(File icona) {
		this.icona = icona;
	}

	public String getDescrizioneAbilita() {
		return descrizioneAbilita;
	}

	public void setDescrizioneAbilita(String descrizioneAbilita) {
		this.descrizioneAbilita = descrizioneAbilita;
	}

	public List<User> getUtentiDichiaranti() {
		return utentiDichiaranti;
	}

	public void setUtentiDichiaranti(List<User> utentiDichiaranti) {
		this.utentiDichiaranti = utentiDichiaranti;
	}
	
	
	
	
	
	
	
	 @Override
	    public String toString() {
	        return "\nNome: "+ nomeAbilita + "  \nDescrizione:"  + descrizioneAbilita + "\n\n";
	    }
	
	
	
	/* codice SQL
	#tabella Abilita
	create table Abilita (
	codice smallint unsigned auto_increment primary key,
	nome varchar(15) not null, 
	icona varchar(30) not null,
	descrizione text not null
);

	 */
}


