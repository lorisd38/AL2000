drop table LesLocations FORCE;
drop table LesReservations FORCE;
drop table LesDVDs FORCE;
drop table LeCatalogue FORCE;
drop table LesClientsA FORCE;
drop table LesPersonnes FORCE;
drop type ens_locations FORCE;
drop type ens_reservations FORCE;
drop type tlocation;
drop type treservation;
drop type tdvd;
drop type tfilm;
drop type tgenres;
drop type tmembre;
drop type tcartemembre;
drop type tclient FORCE;
drop type tacteurs FORCE;
drop type tpersonne;

-- Personne (acteur, real, pro, ou autre)
create type tpersonne as object (
    nom varchar2(20),
    prenom varchar2(20)
);
/

create table LesPersonnes of tpersonne;

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
    date_de_sortie varchar2(20),
    acteurs tacteurs,
    resume varchar2(200),
    affiche_url varchar2(20),
    genres tgenres
);
/

-- Definition du type tdvd
-- tdvd est un objet pointant sur l'objet physique (donc meme apres location il n'est pas retire de le base)
create type tdvd as object (
    film REF tfilm,
    estDispo number(1),
    estReserve number(1)
);
/

-- Creation de la table LesDVDs
create table LesDVDs of tdvd;
/

-- Creation de la table LeCatalogue
create table LeCatalogue of tfilm
    Nested table acteurs Store As TousActeurs;


-- CarteMembre : montant de la carte + date depuis quand cette derniere a montant < 0
create type tcartemembre as object (
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
    carteMembre tcartemembre,
    membre tpersonne
);
/

-- Creation de la table LesCLients
-- Peut recevoir du type tclient ou tmembre
create table LesClientsA of tclient;

-- Creation de la location
create type tlocation as object (
    film REF tfilm,
    dateLoc date,
    dateRet date
);
/

-- Creation de la reservation
create type treservation as object (
    film REF tfilm,
    dateRes date,
    aRetire number(1), -- dvd reserve retire (1 -> le membre a retirer son dvd reserve)
    dvdRetire REF tdvd -- dvd en question
);
/

-- Definition d'une collection libre de references sur tlocation
-- Ensemble location : table de toutes les locations d'une personne
create type ens_locations as Table of REF tlocation;
/

-- Definition d'une collection libre de references sur treservation
-- Ensemble reservation : table de toutes les reservations d'une personne
create type ens_reservations as Table of REF treservation;
/

-- Creation de la table LesLocations
-- Associe un client a une location
create table LesLocations (
    client tclient,
    liste_location ens_locations
)
Nested table liste_location Store As ListeLocationsClient;

-- Creation de la table LesReservations
-- Associe un client (membre) a une resvation
create table LesReservations (
    client tclient,-- Le client peut ne plus être membre un jour (donc on garde tout de meme une trace)
    liste_reservation ens_reservations
)
Nested table liste_reservation Store As ListeReservationsMembre;