drop table lesclients FORCE;
drop table lesdvds FORCE;
drop table lesfilms FORCE;
drop type ens_locations FORCE;
drop type ens_reservations FORCE;
drop type ens_categories FORCE;
drop type tcartemembre;
drop type tcartes;
drop type tcategorie;
drop type tpersonne;
drop type tacteurs;
drop type tdvd;
drop type tlocation;
drop type treservation;
drop type tmembre;
drop type tclient FORCE;



create type tcartemembre(idCarte number(5),montant number(5), dateNeg date);

create type tcartes as Varray(10) of tcartemembre;

create type tcategorie(nomcat varchar2(20));

create type ens_categories as Table of tcategorie;

create type tpersonne(nom varchar2(20), prenom varchar2(20));

create type tacteurs as Varray(10) of tpersonne;

Create type tfilm(
    idfilm number(7),
    titre varchar2(20),
    producteur tpersonne,
    realisateur tpersonne,
    date_de_sortie varchar2(20),
    acteurs tacteurs,
    liste_categories ens_categories,
    resume varchar2(200),
    affiche_url varchar2(200)
)
nested table liste_categories store as categories;

create table lesfilms of tfilm;

create type tdvd(
    iddvd number(8),
    film REF lesfilms,
    estDispo boolean,
    estReserve boolean
);

create table lesdvds of tdvd;

create type tlocation(
    dvd REF lesdvds,
    dateloc date,
    dateret date
);

create type treservation(
    film REF lesfilms,
    dateres date,
    dvd REF lesdvds
);

create type ens_reservations as Table of treservation;

create type ens_locations as Table of tlocation;

create type tclient(
    noCB varchar2(20),
    liste_locations ens_locations
)
nested table liste_locations store as locations;

create type tmembre UNDER tclient(
    idCarteMembre varchar2(20),
    titulaire tpersonne,
    noCB varchar2(20),
    liste_reservations ens_reservations
)
nested table liste_reservations store as reservations;

create table lesclients of tclient;
-- create table lesmembres of tmembre;