CREATE DATABASE raf_turisticki_vodic;

USE raf_turisticki_vodic;

CREATE TABLE korisnik (
    email VARCHAR(70) NOT NULL,
    password varchar(255) NOT NULL,
    ime_prezime varchar(50) NOT NULL,
    tip_korisnika ENUM('admin', 'editor') NOT NULL,
    status_korisnika ENUM('active', 'inactive') NOT NULL,
    PRIMARY KEY (email)
);

CREATE TABLE destinacija(
    ime varchar(100) NOT NULL,
    opis text NOT NULL,
    PRIMARY KEY (ime)
);

CREATE TABLE clanak(
    id INT AUTO_INCREMENT,
    naslov varchar(100) NOT NULL,
    tekst text NOT NULL,
    broj_poseta INT DEFAULT 0 NOT NULL,
    autor_email varchar(70) NOT NULL,
    destinacija_ime VARCHAR(100) NOT NULL,
    datum TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (autor_email) REFERENCES korisnik(email),
    FOREIGN KEY (destinacija_ime) REFERENCES destinacija(ime)
);

CREATE TABLE aktivnost (
    naziv VARCHAR(100) NOT NULL,
    PRIMARY KEY (naziv)
);

CREATE TABLE clanak_aktivnost (
    clanak_id INT NOT NULL,
    aktivnost_naziv VARCHAR(100) NOT NULL,
    PRIMARY KEY (clanak_id, aktivnost_naziv),
    FOREIGN KEY (clanak_id) REFERENCES clanak(id),
    FOREIGN KEY (aktivnost_naziv) REFERENCES aktivnost(naziv)
);

CREATE TABLE komentar (
    id INT AUTO_INCREMENT,
    autor_komentara VARCHAR(70) NOT NULL,
    tekst TEXT NOT NULL,
    datum TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    clanak_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (clanak_id) REFERENCES clanak(id)
);
