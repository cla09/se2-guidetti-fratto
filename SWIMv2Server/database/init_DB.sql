drop database swimv2;

#creazione della base dati
create database swimv2;

#selezionare database creato
use swimv2;

#creazione dell'utente che può modificare il database (ASSICURARSI CHE NON SIA GIA' PRESENTE UN UTENTE "admin")

#create USER 'admin'@'localhost' IDENTIFIED BY 'admin';
#grant all on swimv2.* to 'admin'@'localhost';

#########################
#creazione delle tabelle#
#########################

#tabella profilo
create table profilo (
	id bigint unsigned auto_increment,
	nickname varchar(20) unique,
	password varchar(20) not null,
	email varchar(255)  not null,
	nome varchar(20) not null,
	cognome varchar(20) not null,
	avatar varchar(20),
	citta varchar(50),
	sesso enum('M', 'F'),
	anno_nascita year,
	ruolo enum('admin', 'user') default 'user',
	primary key(id)
);

#tabella abilita
create table abilita (
	codice int unsigned auto_increment,
	nome varchar(20) not null, 
	icona varchar(20),
	descrizione varchar(140) not null,
	primary key (codice)
);

#tabella data
create table data_completa (
	id bigint unsigned auto_increment,
	timestamp bigint unsigned unique,
	anno smallint(4) not null,
	mese smallint(2) unsigned not null,
	giorno smallint(2) unsigned not null,
	ora smallint(2) unsigned not null,
	minuto smallint(2) unsigned not null,
	secondo smallint(2) unsigned not null,
	primary key (id)
);

#tabella proposta_abilita
create table proposta_abilita (
	codice int unsigned auto_increment,
	nome varchar(20) not null,
	descrizione varchar(140) not null,
	stato_richiesta enum('visionata', 'nonVisionata') default 'nonVisionata' not null,
	user_proponente varchar(20) not null,
	primary key (codice),
	foreign key(user_proponente) references profilo(nickname)
		#se elimino (aggiorno) un utente allora elimino (aggiorno) anche le sue proposte di nuove abilita
		on delete cascade
		on update cascade
);

#tabella dichiarazione
create table dichiarazione(
	id int unsigned auto_increment,	
	user varchar(20),
	codice_abilita int unsigned,
	numero_feedback int unsigned default 0,
	media_valutazioni int unsigned default 0,
	primary key (id),
	#se elimino (aggiorno) un utente allora elimino (aggiorno) anche le sue dichiarazioni di abilita
	foreign key(user) references profilo(nickname)
		on delete cascade
		on update cascade,
	#non e' possibile eliminare un'abilita che e' dichiarata da qualche utente (le operazioni di eliminazione e aggiornamento sono respinte)
	foreign key(codice_abilita) references abilita(codice)
		on delete restrict
		on update restrict
);

#tabella amicizia
create table amicizia (
	codice int unsigned auto_increment,
	user_richiedente varchar(20) not null,
	user_destinatario varchar(20) not null,
	momento_richiesta bigint unsigned not null,
	momento_accettazione bigint unsigned,
	primary key (codice),
	#se elimino (aggiorno) lo user richiedente allora tutte le sue richieste di amicizia devono essere eliminate(aggiornate)
	foreign key(user_richiedente) references profilo(nickname)
		on delete cascade
		on update cascade,
	#se elimino (aggiorno) lo user destiatario allora tutte le sue richieste di amicizia devono essere eliminate(aggiornate)
	foreign key(user_destinatario) references profilo(nickname)
		on delete cascade
		on update cascade,
	#non e' possibile eliminare (aggiornare) una data associata al momento di richiesta di un'amicizia
	foreign key(momento_richiesta) references data_completa(id)
		on delete restrict
		on update restrict,
	#non e' possibile eliminare (aggiornare) una data associata al momento di accettazione di un'amicizia
	foreign key(momento_accettazione) references data_completa(id)
		on delete restrict
		on update restrict
);

#tabella aiuto
create table aiuto (
	codice int unsigned auto_increment,
	descrizione varchar(140) not null,
	user_richiedente varchar(20) not null,
	user_destinatario varchar(20) not null,
	codice_abilita int unsigned not null,
	momento_richiesta bigint unsigned not null,
	momento_accettazione bigint unsigned,
	primary key (codice),
	#se elimino (aggiorno) lo user richiedente allora tutte le sue richieste di aiuto devono essere eliminate(aggiornate)
	foreign key(user_richiedente) references profilo(nickname)
		on delete cascade
		on update cascade,
	#se elimino (aggiorno) lo user destiatario allora tutte le sue richieste di aiuto devono essere eliminate(aggiornate)
	foreign key(user_destinatario) references profilo(nickname)
		on delete cascade
		on update cascade,
	#non e' possibile eliminare (aggiornare) l'abilita oggetto di una richiesta di aiuto
	foreign key(codice_abilita) references abilita(codice)
		on delete restrict
		on update restrict,
	#non e' possibile eliminare (aggiornare) una data associata al momento di richiesta di un aiuto
	foreign key(momento_richiesta) references data_completa(id)
		on delete restrict
		on update restrict,
	#non e' possibile eliminare (aggiornare) una data associata al momento di accettazione di un aiuto
	foreign key(momento_accettazione) references data_completa(id)
		on delete restrict
		on update restrict
);

#tabella feedback
create table feedback (
	codice_aiuto int unsigned auto_increment,
	valutazione_numerica smallint(1) not null,
	valutazione_estesa varchar(140) not null,
	momento_rilascio bigint unsigned not null,
	primary key (codice_aiuto),
	#se elimino (aggiorno) l'aiuto allora elimino (aggiorno) anche il feedback ad esso associato
	foreign key(codice_aiuto) references aiuto(codice)
		on delete cascade
		on update cascade,
	#non e' possibile eliminare (aggiornare) una data associata al momento di rilascio di un feedback
	foreign key(momento_rilascio) references data_completa(id)
		on delete restrict
		on update restrict
);

#Creazione dell'utente amministratore
insert into profilo values (default, 'admin', 'admin', 'inserisci la tua mail', 
							'inserisci nome', 'inserisci cognome',
							null, null, null, null, 'admin');

#Creazione della data utilizzata convenzionalmente per indicare il momentoAccettazione null
#insert into data_completa values( '9999-12-31 23:59:59',9999, 12, 31, 23, 59, 59);