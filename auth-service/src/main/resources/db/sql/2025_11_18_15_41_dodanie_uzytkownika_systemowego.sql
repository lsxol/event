--liquibase formatted sql
--changeset dkazmierczak:2025_11_18_15_41_dodanie_uzytkownika_systemowego

INSERT INTO uzytkownik(version_id, id, created_by, create_date, modified_date, email, password_hash, role)
VALUES (0, '3f4b1a54-8e3e-4e3c-9f31-0b4a4d7d8d21', null, now(), now(), 'test@test.pl', 'test', 'ADMIN')