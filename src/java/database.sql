/**
 * Author:  rmoalic
 * Created: 29 mars 2019
 */

create table film (
    id int primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    nom varchar(25) not null,
    producteur varchar(25) not null,
    sortie date not null
);

create table acteur (
    id int primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    nom varchar(25) not null,
    prenom varchar(25) not null,
    cote int not null
);

create table joue (
    id_film int references film(id),
    id_acteur int references acteur(id)
);

create table programation (
    id int primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    d date not null,
    film int not null references film(id)
);