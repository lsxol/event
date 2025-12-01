--liquibase formatted sql
--changeset dkazmierczak:2025_11_24_15_39_dodanie_tabeli_profil_uzytkownika

CREATE TABLE profil_uzytkownika
(
    version_id                 INTEGER          NOT NULL DEFAULT 0,
    id                         UUID primary key NOT NULL,
    created_by                 UUID             NULL,
    create_date                TIMESTAMP        NULL,
    modified_date              TIMESTAMP        NULL,
    email                      varchar unique   not null,
    numer_telefonu             varchar unique,
    imie                       VARCHAR,
    nazwisko                   VARCHAR,
    numer_dokumentu_tozsamosci VARCHAR
);

create table profil_uzytkownika_audit as
select *
from profil_uzytkownika with no data;
ALTER TABLE profil_uzytkownika_audit
    drop
        column created_by,
    drop
        column modified_date,
    drop
        column create_date,
    drop
        column version_id;
ALTER table profil_uzytkownika_audit
    ADD column revision_id   integer  not null,
    ADD column revision_type smallint not null,
    ADD CONSTRAINT "PK_profil_uzytkownika_audit" PRIMARY KEY (revision_id, id),
    add constraint FK_revision_info foreign key (revision_id) references revision_info;