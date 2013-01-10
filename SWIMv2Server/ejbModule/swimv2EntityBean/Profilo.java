package swimv2EntityBean;

import java.io.Serializable;
import javax.persistence.*;

import commons.EnumSesso;


@Entity
@DiscriminatorColumn(name = "ruolo", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("X")
@Table(name = "profilo")
public abstract class Profilo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "nickname")
	private String nickname;
	
	@Column(name = "pwd")
	private String password;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "cognome")
	private String cognome;
	
	@Column(name = "sesso")
	private EnumSesso sesso;
	
	@Column(name = "anno_nascita")
	private int annoNascita;
	
	/*
	 * Metodi costruttori e di creazione EnityBean dai Pojo
	 */	
	
	
	
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

	public EnumSesso getSesso() {
		return sesso;
	}

	public void setSesso(EnumSesso sesso) {
		this.sesso = sesso;
	}

	public int getAnnoNascita() {
		return annoNascita;
	}

	public void setAnnoNascita(int annoNascita) {
		this.annoNascita = annoNascita;
	}
	
	/*
	 * *******************************
	 * Fine metodi setter e getter   *
	 *********************************
	 */
	
	
	
	
		
	/*
	 * 
	 * codice SQL di creazione
	#tabella profilo
	create table profilo (
	nickname varchar(15) primary key,
	pwd	varchar(10) not null,
	email varchar(25) not null,
	nome varchar(20) not null,
	cognome varchar(20) not null,
	avatar varchar(30) not null,
	citta varchar(20) not null,
	sesso enum('M', 'F'),
	anno_nascita year,
	flag_admin enum('si', 'no') default 'no'
	);

	*/

}
