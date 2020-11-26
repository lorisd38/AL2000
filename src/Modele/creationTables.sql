drop table LesLocations FORCE;
drop table LesReservations FORCE;
drop table LesDVDs FORCE;
drop table LeCatalogue FORCE;
drop table LesClientsA FORCE;
drop table LesPersonnesA FORCE;
drop type ens_locations FORCE;
drop type ens_reservations FORCE;
drop type tclient FORCE;
drop type tacteurs FORCE;
drop type tfilm;
drop type tgenres;
drop type tmembre;
drop type tcartemembreA;
drop type tpersonne;
drop type tlocationA;
drop type treservation;
drop type tdvd;

-- Personne (acteur, real, pro, ou autre)
create type tpersonne as object (
                                    nom varchar2(20),
                                    prenom varchar2(20)
                                );
/

create table LesPersonnesA of tpersonne;

-- Definition d'une collection libre de reference sur tpersonne
-- Acteurs : table de personnes
create type tacteurs as Table of REF tpersonne;
/

-- Definition d'une collection statique
-- Genres : liste de genres pour un film (comedie, action, etc...)
create type tgenres as Varray(10) of varchar2(20);
/

-- Film
create type tfilm as object (
                                titre varchar2(20),
                                producteur REF tpersonne,
                                realisateur REF tpersonne,
                                date_de_sortie date,
                                acteurs tacteurs,
                                resume varchar2(200),
                                affiche_url varchar2(20),
                                genres tgenres
                            );
/

-- Definition du type tdvd
-- tdvd est un objet pointant sur l'objet physique (donc meme apres location il n'est pas retire de le base)
create type tdvd as object (
                               codeBarre number(12),
                               film REF tfilm,
                               estDispo number(1),
                               estReserve number(1)
                           );
/

-- Creation de la table LesDVDs
create table LesDVDs of tdvd(UNIQUE(codeBarre));
/

-- Creation de la table LeCatalogue
create table LeCatalogue of tfilm(
    UNIQUE(titre))
    Nested table acteurs Store As TousActeurs;


-- CarteMembre : montant de la carte + date depuis quand cette derniere a montant < 0
create type tcartemembreA as object (
                                        montant number(5),
                                        dateNeg date
                                    );
/

-- Definition du type client
create type tclient as object (
    noCB varchar2(20)
                              )
    not final;
/

-- Definition du sous-type de client, membre
create type tmembre under tclient (
                                      carteMembre tcartemembreA,
                                      membre tpersonne
                                  );
/

-- Creation de la table LesCLients
-- Peut recevoir du type tclient ou tmembre
create table LesClientsA of tclient;

-- Creation de la location
create type tlocationA as object (
                                     dvd REF tdvd,
                                     dateLoc date,
                                     dateRet date
                                 );
/

-- Creation de la reservation
create type treservation as object (
                                       film REF tfilm,
                                       dateRes date,
                                       dvdRetire REF tdvd -- dvd en question
                                   );
/

-- Definition d'une collection libre de references sur tlocation
-- Ensemble location : table de toutes les locations d'une personne
create type ens_locations as Table of tlocationA;
/

-- Definition d'une collection libre de references sur treservation
-- Ensemble reservation : table de toutes les reservations d'une personne
create type ens_reservations as Table of treservation;
/

-- Creation de la table LesLocations
-- Associe un client a une location
create table LesLocations (
                              clientCB varchar2(20),
                              liste_location ens_locations
)
    Nested table liste_location Store As ListeLocationsClient;

-- Creation de la table LesReservations
-- Associe un client (membre) a une resvation
create table LesReservations (
                                 clientCB varchar2(20),-- Le client peut ne plus être membre un jour (donc on garde tout de meme une trace)
                                 liste_reservation ens_reservations
)
    Nested table liste_reservation Store As ListeReservationsMembre;

INSERT INTO LesPersonnesA values ('bob','dylan');
INSERT INTO LesPersonnesA values ('georges','michael');
INSERT INTO LesPersonnesA values ('steven','spielberg');
INSERT INTO LesPersonnesA values ('bon','dylon');
INSERT INTO LesPersonnesA values ('J','cameron');

INSERT INTO LeCatalogue values
('bonjour',(select REF(p) from LesPersonnesA p
            where p.nom = 'georges'),(select REF(p) from LesPersonnesA p
                                      where p.nom = 'bon'),
 DATE '2001-08-10',tacteurs((select REF(p) from LesPersonnesA p
                             where p.nom = 'steven')),'c est la merde','qgqgqrfhgqfhqhf',
 tgenres('horror'));

INSERT INTO LeCatalogue values
('bonjour2',(select REF(p) from LesPersonnesA p
             where p.nom = 'bon'),(select REF(p) from LesPersonnesA p
                                   where p.nom = 'georges'),
 DATE '2001-08-10',tacteurs((select REF(p) from LesPersonnesA p
                             where p.nom = 'bon')),'c est la merde','qgqgqrfhgqfhqhf',
 tgenres('action', 'thriller'));

INSERT INTO LeCatalogue values
('bonjour3',(select REF(p) from LesPersonnesA p
             where p.nom = 'bob'),(select REF(p) from LesPersonnesA p
                                   where p.nom = 'J'),
 DATE '2001-08-10',tacteurs((select REF(p) from LesPersonnesA p
                             where p.nom = 'georges')),'c est la merde','qgqgqrfhgqfhqhf',
 tgenres('horror', 'romance'));

INSERT INTO LeCatalogue values
('bonjour5',(select REF(p) from LesPersonnesA p
             where p.nom = 'bob'),(select REF(p) from LesPersonnesA p
                                   where p.nom = 'bon'),
 DATE '2001-08-10',tacteurs((select REF(p) from LesPersonnesA p
                             where p.nom = 'steven'),(select REF(p) from LesPersonnesA p
                                                      where p.nom = 'J')),'c est la merde','qgqgqrfhgqfhqhf',
 tgenres('horror', 'romance'));

INSERT INTO LesDVDs VALUES (1234,(select REF(f) from LeCatalogue f where f.titre = 'bonjour'),1,0);

INSERT INTO LesDVDs VALUES (1235,(select REF(f) from LeCatalogue f where f.titre = 'bonjour2'),1,0);

INSERT INTO LesDVDs VALUES (1236,(select REF(f) from LeCatalogue f where f.titre = 'bonjour3'),1,0);

INSERT INTO LesDVDs VALUES (1237,(select REF(f) from LeCatalogue f where f.titre = 'bonjour'),1,0);

INSERT INTO LesClientsA VALUES (tclient(103485409));

INSERT INTO LesClientsA VALUES (tclient(45));
INSERT INTO LesClientsA VALUES (tmembre(245, tcartemembreA(30, DATE '2001-08-10'), tpersonne('George','bob')));
INSERT INTO LesClientsA VALUES (tmembre(300, tcartemembreA(500, DATE '2012-06-14'), tpersonne('Axel','ASR')));

INSERT INTO LesLocations VALUES (300, ens_locations(tlocationA((select REF(d) from lesDvds d
                                                                where d.codeBarre = 1234),DATE '2001-08-10',null)));
INSERT INTO LesLocations VALUES (300, ens_locations(tlocationA((select REF(d) from lesDvds d
                                                                where d.codeBarre = 1235),DATE '2001-08-13',null),
                                                    tlocationA((select REF(d) from lesDvds d
                                                                where d.codeBarre = 1236),DATE '2001-08-13',null)));
INSERT INTO LesLocations VALUES (45, ens_locations(tlocationA((select REF(d) from lesDvds d
                                                               where d.codeBarre = 1234),DATE '2001-08-17',null)));

INSERT INTO LesReservations VALUES (300, ens_reservations(treservation((select REF(c) from LeCatalogue c
                                                                        where c.titre = 'bonjour'),DATE '2001-08-10',null)));

INSERT INTO LesReservations VALUES ('300', ens_reservations(treservation((select REF(c) from LeCatalogue c
                                                                          where c.titre = 'bonjour2'),DATE '2001-08-30',null),
                                                            treservation((select REF(c) from LeCatalogue c
                                                                          where c.titre = 'bonjour5'),DATE '2003-08-30',null)));