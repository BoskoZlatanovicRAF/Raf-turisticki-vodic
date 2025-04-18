INSERT INTO aktivnost (naziv) VALUES 
('skijanje'),
('planinarenje'),
('plivanje'),
('kulturni događaji'),
('muzika'),
('gastronomija'),
('noćni život'),
('istorija'),
('festival');

INSERT INTO destinacija (ime, opis) VALUES 
('Beograd', 'Glavni grad Srbije, poznat po svojoj bogatoj istoriji i kulturi.'),
('Novi Sad', 'Drugi po veličini grad u Srbiji, domaćin poznatog EXIT festivala.'),
('Niš', 'Grad na jugoistoku Srbije, poznat po svojoj tvrđavi i bogatoj istoriji.');


INSERT INTO korisnik (email, password, ime_prezime, tip_korisnika, status_korisnika) VALUES 
('admin@gmail.com', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'Admin Ime', 'admin', 'active'),
('editor1@gmail.com', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'Editor Prvi', 'editor', 'active'),
('editor2@example.com', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'Editor Drugi', 'editor', 'inactive');
('bosko@gmail.com', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'Bosko Boskovic', 'editor', 'active');

-- Unos podataka za tabelu clanak
INSERT INTO clanak (naslov, tekst, broj_poseta, autor_email, destinacija_ime) VALUES
('Top 10 Beogradskih Kafića', 'Beograd je poznat po svojim kafićima...', 123, 'bosko@gmail.com', 'Beograd'),
('Najlepše šetnje u Beogradu', 'Beograd ima mnogo prelepih staza za šetnju...', 95, 'bosko@gmail.com', 'Beograd'),
('Kultura i umetnost u Beogradu', 'Beograd je centar kulture i umetnosti...', 76, 'bosko@gmail.com', 'Beograd'),
('Najbolji restorani u Beogradu', 'Beograd je poznat po svojoj gastronomskoj ponudi...', 88, 'bosko@gmail.com', 'Beograd'),
('Beogradski noćni život', 'Noćni život u Beogradu je veoma dinamičan...', 150, 'bosko@gmail.com', 'Beograd'),
('Exit Festival: Sve što treba da znate', 'EXIT je jedan od najvećih muzičkih festivala...', 300, 'bosko@gmail.com', 'Novi Sad'),
('Novi Sad: Grad muzike', 'Novi Sad je poznat po svojim muzičkim događajima...', 175, 'bosko@gmail.com', 'Novi Sad'),
('Istorija Novog Sada', 'Novi Sad ima bogatu istoriju...', 120, 'editor1@gmail.com', 'Novi Sad'),
('Gastronomska ponuda Novog Sada', 'Novi Sad nudi širok izbor hrane...', 110, 'editor1@gmail.com', 'Novi Sad'),
('Najlepši parkovi u Novom Sadu', 'Novi Sad ima mnogo prelepih parkova...', 80, 'editor1@gmail.com', 'Novi Sad'),
('Tvrđava u Nišu: Svedok istorije', 'Niška tvrđava je jedan od najvažnijih spomenika...', 145, 'editor1@gmail.com', 'Niš'),
('Niš: Grad festivala', 'Niš je domaćin mnogih festivala...', 160, 'editor1@gmail.com', 'Niš'),
('Najlepša mesta u Nišu', 'Otkrijte najlepša mesta u Nišu...', 130, 'editor1@gmail.com', 'Niš'),
('Tradicionalna kuhinja Niša', 'Upoznajte se sa tradicionalnim jelima Niša...', 115, 'editor1@gmail.com', 'Niš'),
('Kulturna dešavanja u Nišu', 'Niš je bogat kulturnim događajima...', 100, 'editor1@gmail.com', 'Niš'),
('Beograd: Turistički vodič', 'Sve što treba da znate o Beogradu...', 95, 'editor1@gmail.com', 'Beograd'),
('Novi Sad: Turistički vodič', 'Sve što treba da znate o Novom Sadu...', 85, 'editor1@gmail.com', 'Novi Sad'),
('Niš: Turistički vodič', 'Sve što treba da znate o Nišu...', 95, 'editor1@gmail.com', 'Niš'),
('Beogradski festival piva', 'Sve o najpoznatijem festivalu piva...', 105, 'editor1@gmail.com', 'Beograd'),
('Najlepši vidikovci u Beogradu', 'Beograd ima mnogo prelepih vidikovaca...', 80, 'editor1@gmail.com', 'Beograd'),
('Novi Sad: Noćni život', 'Noćni život u Novom Sadu je veoma aktivan...', 130, 'bosko@gmail.com', 'Novi Sad'),
('Niška banja: Zdravlje i relaksacija', 'Sve o lepotama Niške banje...', 140, 'bosko@gmail.com', 'Niš'),
('Beogradski zoološki vrt', 'Sve što treba da znate o beogradskom zoološkom vrtu...', 95, 'bosko@gmail.com', 'Beograd'),
('Novi Sad: Istorijski spomenici', 'Novi Sad ima bogatu istoriju i spomenike...', 110, 'bosko@gmail.com', 'Novi Sad'),
('Niš: Kulturna baština', 'Kulturna baština Niša je bogata i raznolika...', 120, 'bosko@gmail.com', 'Niš');


-- Unos podataka za tabelu clanak_aktivnost
INSERT INTO clanak_aktivnost (clanak_id, aktivnost_naziv) VALUES
(1, 'gastronomija'),
(2, 'planinarenje'),
(3, 'plivanje'),
(4, 'gastronomija'),
(5, 'noćni život'),
(6, 'festival'),
(7, 'muzika'),
(8, 'istorija'),
(9, 'gastronomija'),
(10, 'planinarenje'),
(11, 'istorija'),
(12, 'gastronomija'),
(13, 'planinarenje'),
(14, 'istorija'),
(15, 'festival'),
(16, 'gastronomija'),
(17, 'kulturni događaji'),
(18, 'gastronomija'),
(19, 'festival'),
(20, 'gastronomija'),
(21, 'festival'),
(22, 'gastronomija'),
(23, 'noćni život'),
(24, 'muzika'),
(25, 'skijanje');


-- Unos podataka za tabelu komentar
INSERT INTO komentar (autor_komentara, tekst, datum, clanak_id) VALUES
('Petar Petrović', 'Odličan članak, veoma informativno!', '2024-05-21 12:34:56', 1),
('Ana Anić', 'Slažem se sa Petrom, ovo je baš korisno.', '2024-05-21 13:45:12', 1),
('Jovan Jovanović', 'Hvala na informacijama!', '2024-05-21 14:23:45', 2),
('Ivana Ivanović', 'Jedva čekam da posetim ovo mesto!', '2024-05-21 16:16:34', 3),
('Marko Marković', 'Odlične preporuke!', '2024-05-21 17:45:20', 4),
('Milica Milić', 'Veoma korisno, hvala!', '2024-05-21 18:45:20', 5),
('Nenad Nedić', 'Ima li još članaka na ovu temu?', '2024-05-21 17:12:34', 6),
('Jelena Jelić', 'Hvala na preporukama, veoma korisno!', '2024-05-21 18:23:45', 7),
('Vladimir Vladimirović', 'Odličan članak, preporučujem svima!', '2024-05-21 19:34:56', 8),
('Maja Majski', 'Baš mi je ovo trebalo, hvala!', '2024-05-21 23:45:12', 9),
('Stefan Stevanović', 'Veoma informativno!', '2024-05-21 23:45:12', 10),
('Marija Marić', 'Hvala na korisnim informacijama!', '2024-05-21 12:34:56', 11),
('Dragan Dragić', 'Odličan vodič, svaka čast!', '2024-05-21 14:23:45', 12),
('Dragana Dragićević', 'Hvala na preporukama!', '2024-05-21 18:23:45', 13),
('Goran Gorić', 'Jedva čekam da isprobam ove aktivnosti!', '2024-05-21 12:34:56', 14),
('Suzana Suzić', 'Veoma korisno, hvala!', '2024-05-21 14:23:45', 15),
('Aleksandar Aleksić', 'Preporučujem svima!', '2024-05-22 03:23:45', 16),
('Aleksandra Aleksić', 'Hvala na vodiču!', '2024-05-22 03:23:45', 17),
('Bojan Bojić', 'Veoma informativno, hvala!', '2024-05-22 14:23:45', 18),
('Bojana Bojićević', 'Hvala na korisnim informacijama!', '2024-05-22 12:34:56', 19),
('Nikola Nikolić', 'Odličan članak, svaka čast!', '2024-05-22 14:23:45', 20),
('Nina Ninić', 'Hvala na preporukama!', '2024-05-22 12:34:56', 21),
('Zoran Zorić', 'Jedva čekam da posetim ove destinacije!', '2024-05-22 14:23:45', 22),
('Zorica Zorićević', 'Veoma korisno, hvala!', '2024-05-22 03:23:45', 23),
('Dejan Dejanović', 'Preporučujem svima!', '2024-05-22 11:45:12', 24);
