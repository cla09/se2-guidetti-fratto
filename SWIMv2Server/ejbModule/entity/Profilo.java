package entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ruolo", discriminatorType = DiscriminatorType.STRING)
@Table(name = "profilo")
public abstract class Profilo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "nickname")
	private String nickname;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@Column(name = "cognome", nullable = false)
	private String cognome;
	
	@Column(name = "avatar")
	private String avatar;		//da verificare come fare l'upload ed il recupero
	
	
	/*
	 * da verificare se l'approccio con DiscriminatorColumn funziona (bisogna avere il login per farlo)
	 * 
	 @Column(name = "ruolo")
	 private String ruolo;
	 */
	
	/*
	 * metodo costruttore
	 */
	public Profilo(){
	}
	
	
	/*
	 * *******************************
	 * Inizio metodi setter e getter *
	 *********************************
	 */
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

/*
	public String getRuolo() {
		return ruolo;
	}


	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

*/
	
	/*
	 * *******************************
	 * Fine metodi setter e getter   *
	 *********************************
	 */
	
	
	
/*
 * codice SQL:
 * 
 * #tabella profilo
create table profilo (
	nickname varchar(15) primary key,
	pwd	varchar(20) not null,
	email varchar(25) not null,
	nome varchar(20) not null,
	cognome varchar(20) not null,
	avatar varchar(30),
	citta varchar(20),
	sesso enum('M', 'F'),
	anno_nascita year,
	ruolo enum('admin', 'user') default 'user'
);	
 */


}
