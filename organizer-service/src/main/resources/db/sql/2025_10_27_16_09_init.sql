--liquibase formatted sql
--changeset dkazmierczak:2025_10_27_16_09_init
CREATE TABLE revision_info
(
    revision_id   integer not null,
    rev_timestamp BIGINT  NOT NULL,
    uzytkownik_id uuid    NOT NULL
);
create sequence revision_info_s INCREMENT 1 START 1;
create sequence hibernate_sequence INCREMENT 1 START 1;
ALTER TABLE revision_info
    ADD CONSTRAINT "auth.revision_info" PRIMARY KEY (revision_id);