drop table LesLocationsA FORCE;
drop table LesReservationsA FORCE;
drop table LesLocations FORCE;
drop table LesReservations FORCE;
drop table LesDVDsA FORCE;
drop table LeCatalogue FORCE;
drop table LesClientsA FORCE;
drop table LesClientsAL FORCE;
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
drop type tlocation;
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
    titre varchar2(100),
    producteur varchar2(40),
    realisateur REF tpersonne,
    date_de_sortie date,
    acteurs tacteurs,
    ageLimite number(3),
    resume varchar2(2000),
    affiche_url varchar2(400),
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
create table LesDVDsA of tdvd(UNIQUE(codeBarre));
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
create table LesClientsAL of tclient;

-- Creation de la table LesLocations
-- Associe un client a une location
create table LesLocationsA (
    clientCB varchar2(20),
    dvd REF tdvd,
    dateLoc date,
    dateRet date
);
/

-- Creation de la table LesReservations
-- Associe un client (membre) a une resvation
create table LesReservationsA (
    clientCB varchar2(20), -- Le client peut ne plus être membre un jour (donc on garde tout de meme une trace)
    film REF tfilm,
    dateRes date,
    dvdRetire REF tdvd
);
/