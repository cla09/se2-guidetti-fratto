#Inserimento tuple tabella profilo
insert into profilo values (default, 'cla09', 'claudiofratto', 'cla09.com', 'claudio', 'fratto', null, 'sorbo', 'M', 1990, 'user');
insert into profilo values (default, 'euge', 'eugeguid', 'euge.com', 'eugenio', 'guidetti', null, 'ferrara', 'M', '1990', 'user');
insert into profilo values (default, 'kikka', 'kik90', 'kik.com', 'federica', 'garibaldi', null, 'pisa', 'F', 1990, 'user');
insert into profilo values (default, 'franco', 'ciccio2', 'franco.com', 'francesco', 'rizzo', null, 'palermo', 'M', 1990, 'user');

#select * from profilo;

#Inserimento tuple tabella abilita
insert into abilita values(default,'cuoco', null, 'chef per matrimoni');
insert into abilita values(default, 'elettricista', null, 'piccole riparazioni elettriche');
insert into abilita values(default, 'meccanico', null, 'pulizia carburatore auto');

#select * from abilita;


#Inserimento tuple tabella proposta abilita
insert into proposta_abilita values(default, 'giocoliere', 'per feste private', default, 'cla09');
insert into proposta_abilita values(default, 'bagnino', 'da spiaggia', default, 'euge');
insert into proposta_abilita values(default, 'baby-sitter', 'nottrurno', 'visionata', 'kikka');

#select * from proposta_abilita;

#Inserimento tuple tabella dichiarazione
insert into dichiarazione values(default, 'cla09', 1, default, default);
insert into dichiarazione values(default,'euge', 1, default, default);
insert into dichiarazione values(default,'kikka', 3, default, default);
insert into dichiarazione values(default,'franco', 2, default, default);

#select * from dichiarazione;

#Inserimento tuple tabella amicizia
insert into amicizia values(default, 'cla09', 'kikka', '2013-01-01 12:00:01', '2013-01-08 11:00:01');

#select * from amicizia;

#Inserimento tuple tabella aiuto
insert into aiuto values(default, 'sono disperata', 'kikka', 'cla09', 1, '2013-01-01 12:00:01', '2013-01-08 11:00:01');
insert into aiuto values(default, 'aiutami', 'cla09', 'kikka', 3, '2013-01-01 12:00:01', '2013-01-08 11:00:01');
insert into aiuto values(default, 'mi serve una mano', 'euge', 'franco', 2, '2013-01-01 12:00:01', '2013-01-08 11:00:01');
insert into aiuto values(default, 'mi aiuti?', 'euge', 'franco', 2, '2013-01-08 11:00:01', null);

#select * from aiuto;

#Inserimento tabella feedback
insert into feedback values(1, 4, 'ottimo lavoro', '2013-01-08 11:00:01');

#select * from feedback;