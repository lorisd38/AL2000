drop table lesclients;
drop table lesdvds;
drop table lesfilms;
drop type ens_locations FORCE;
drop type ens_reservations FORCE;
drop type ens_categories FORCE;
drop type tmembre;
drop type tclient FORCE;
drop type treservation;
drop type tlocation;
drop type tdvd;
drop type tfilm;
drop type tacteurs;
drop type tpersonne;
drop type tcategorie;
drop type tcartes;
drop type tcartemembre;


create type tcartemembre as object(
    idCarte number(5),
    montant number(5), dateNeg date
);
/

create type tcartes as Varray(10) of tcartemembre;
/

create type tcategorie as object(nomcat varchar2(20));
/

create type ens_categories as Table of tcategorie;
/

create type tpersonne as object(
    nom varchar2(20),
    prenom varchar2(20)
);
/

create type tacteurs as Varray(10) of tpersonne;
/

create type tfilm as object(
    idfilm number(7),
    titre varchar2(20),
    producteur tpersonne,
    realisateur tpersonne,
    date_de_sortie varchar2(20),
    acteurs tacteurs,
    resume varchar2(200),
    affiche_url varchar2(200)
);
/

create table lesfilms
(
    film             tfilm,
    liste_categories ens_categories
)
    nested table liste_categories store as categories;
/

create type tdvd as object(
    iddvd number(8),
    film REF tfilm,
    estDispo number(1),
    estReserve number(1)
);
/

create table lesdvds of tdvd;
/

create type tlocation as object(
    dvd REF tdvd,
    dateloc date,
    dateret date
);
/

create type treservation as object(
    idReservation number(8),
    film REF tfilm,
    dateres date,
    dvd REF tdvd
);
/

create type ens_reservations as varray(3) of REF treservation;
/

create table lesreservation of treservation;
/

create type ens_locations as Table of tlocation;
/

create type tclient as object(
    noCB varchar2(20)
);
/

create type tmembre UNDER tclient(
    idCarteMembre varchar2(20),
    titulaire tpersonne,
    noCB varchar2(20),
    liste_reservations ens_reservations
);
/

create table lesclients(
    clients tclient,
    liste_locations ens_locations
)
    nested table liste_locations store as locations
;
/