--liquibase formatted sql
--changeset dkazmierczak:2025_10_28_15_39_dodanie_tabeli_uzytkownika

CREATE TABLE uzytkownik
(
    version_id    INTEGER          NOT NULL DEFAULT 0,
    id            UUID primary key NOT NULL,
    created_by    UUID             NULL,
    create_date   TIMESTAMP        NULL,
    modified_date TIMESTAMP        NULL,
    email         varchar unique   not null,
    password_hash varchar          not null,
    role          VARCHAR          NOT NULL
);

create table uzytkownik_audit as
select *
from uzytkownik with no data;
ALTER TABLE uzytkownik_audit
    drop
        column created_by,
    drop
        column modified_date,
    drop
        column create_date,
    drop
        column version_id;
ALTER table uzytkownik_audit
    ADD column revision_id   integer  not null,
    ADD column revision_type smallint not null,
    ADD CONSTRAINT "PK_uzytkownik_audit" PRIMARY KEY (revision_id, id),
    add constraint FK_revision_info foreign key (revision_id) references revision_info;