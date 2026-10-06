/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 10.4.18-MariaDB : Database - auto_oprema
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`auto_oprema` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `auto_oprema`;



DROP TABLE IF EXISTS `Prodavac`;

CREATE TABLE `Prodavac` (
  `ProdavacID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Ime` VARCHAR(50) NOT NULL,
  `Prezime` VARCHAR(50) NOT NULL,
  `KorisnickoIme` VARCHAR(30) NOT NULL,
  `Lozinka` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`ProdavacID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT INTO `Prodavac` VALUES 
(1,'Marija','Vlaisavljevic','marija', 'marija'),
(2,'Nikola','Jovanovic','nikola','nikola'),
(3,'Milica','Petrovic','milica','milica'),
(4,'Stefan','Markovic','stefan','stefan'),
(5,'Jovana','Ilic','jovana','jovana');



DROP TABLE IF EXISTS `Prodavnica`;

CREATE TABLE `Prodavnica` (
  `ProdavnicaID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Naziv` VARCHAR(50) NOT NULL,
  `Satnica` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`ProdavnicaID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT INTO `Prodavnica` VALUES 
(1,'Auto Oprema Beograd', 800.00),
(2,'Auto Oprema Novi Sad', 750.00),
(3,'Auto Oprema Nis', 700.00);



DROP TABLE IF EXISTS `ProdavacProdavnica`;

CREATE TABLE `ProdavacProdavnica` (
  `ProdavnicaID` BIGINT(10) UNSIGNED NOT NULL,
  `ProdavacID` BIGINT(10) UNSIGNED NOT NULL,
  `Datum` DATE NOT NULL,
  `Dnevnica` DECIMAL(10,2) NOT NULL,
  `BrojSati` INT(7) NOT NULL,
  PRIMARY KEY (`ProdavnicaID`, `ProdavacID`),
  CONSTRAINT `fk_prodavnica_id` FOREIGN KEY (`ProdavnicaID`) REFERENCES `Prodavnica` (`ProdavnicaID`),
  CONSTRAINT `fk_prodavac_id2` FOREIGN KEY (`ProdavacID`) REFERENCES `Prodavac` (`ProdavacID`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;



INSERT INTO `ProdavacProdavnica` VALUES 
(1,1,'2025-10-15',6400.00,8),
(2,2,'2025-10-15',6000.00,8),
(3,3,'2025-10-16',5600.00,8),
(1,4,'2025-10-17',6400.00,8),
(2,5,'2025-10-18',6000.00,8);



DROP TABLE IF EXISTS `Grad`;

CREATE TABLE `Grad` (
  `GradID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Naziv` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`GradID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT INTO `Grad` VALUES
(1,'Beograd'),
(2,'Kragujevac'),
(3,'Vranje'),
(4,'Novi Sad'),
(5,'Nis'),
(6,'Pancevo'),
(7,'Subotica');



DROP TABLE IF EXISTS `Kupac`;

CREATE TABLE `Kupac` (
  `KupacID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Ime` VARCHAR(30) NOT NULL,
  `Prezime` VARCHAR(30) NOT NULL,
  `Email` VARCHAR(50) NOT NULL,
  `Telefon` VARCHAR(30) NOT NULL,
  `GradID` BIGINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`KupacID`),
  CONSTRAINT `fk_grad_id` FOREIGN KEY (`GradID`) REFERENCES `Grad` (`GradID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT INTO `Kupac` VALUES 
(1,'Marko','Nikolic','marko.nikolic@mail.com', '063111111', 1),
(2,'Petar','Jovanovic','petar.jovanovic@mail.com', '066222222', 2),
(3,'Ana','Petrovic','ana.petrovic@mail.com', '062333333', 3),
(4,'Jelena','Stankovic','jelena.stankovic@mail.com', '064444555', 4),
(5,'Milan','Ilic','milan.ilic@mail.com', '065555666', 5),
(6,'Stefan','Kostic','stefan.kostic@mail.com', '067777888', 6),
(7,'Nikola','Pavlovic','nikola.pavlovic@mail.com', '069999000', 7);



DROP TABLE IF EXISTS `AutoOprema`;

CREATE TABLE `AutoOprema` (
  `AutoOpremaID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Naziv` VARCHAR(50) NOT NULL,
  `Opis` VARCHAR(300) NOT NULL,
  `Cena` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`AutoOpremaID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT INTO `AutoOprema` (`AutoOpremaID`, `Naziv`, `Opis`, `Cena`) VALUES 
(1, 'Patosnice gumene', 'Gumene patosnice univerzalne, otporne na vodu i prljavstinu.', 3500.00),
(2, 'Presvlake za sedista', 'Set presvlaka za prednja i zadnja sedista, crna boja.', 8200.00),
(3, 'LED sijalice H7', 'LED sijalice za farove, bela svetlost, visok intenzitet.', 4500.00),
(4, 'Nosac telefona', 'Magnetni nosac telefona za ventilaciju automobila.', 1800.00),
(5, 'Kamera za rikverc', 'Vodootporna kamera za parkiranje sa nocnim rezimom.', 6900.00),
(6, 'Parking senzori', 'Set parking senzora sa zvucnim upozorenjem.', 7500.00),
(7, 'Krovni nosaci', 'Univerzalni aluminijumski krovni nosaci za automobil.', 14500.00),
(8, 'Auto punjac USB-C', 'Brzi auto punjac sa USB-C i USB-A prikljuckom.', 2200.00),
(9, 'Navlake za volan', 'Kožna navlaka za volan, univerzalna velicina.', 2600.00),
(10, 'Aparat za gasenje pozara', 'Mali protivpozarni aparat namenjen za automobil.', 3200.00),
(11, 'Komplet prve pomoci', 'Zakonski obavezan komplet prve pomoci za putnicka vozila.', 2500.00),
(12, 'Dizalica za auto', 'Hidraulicna dizalica za podizanje vozila.', 9800.00);



DROP TABLE IF EXISTS `Porudzbina`;

CREATE TABLE `Porudzbina` (
  `PorudzbinaID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `DatumVreme` DATETIME NOT NULL,
  `DatumVremeIsporuke` DATETIME NOT NULL,
  `Iznos` DECIMAL(10,2) NOT NULL,
  `KupacID` BIGINT(10) UNSIGNED NOT NULL,
  `ProdavacID` BIGINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`PorudzbinaID`),
  CONSTRAINT `fk_kupac_id` FOREIGN KEY (`KupacID`) REFERENCES `Kupac` (`KupacID`),
  CONSTRAINT `fk_prodavac_id` FOREIGN KEY (`ProdavacID`) REFERENCES `Prodavac` (`ProdavacID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;



INSERT INTO `Porudzbina` VALUES 
(1,'2026-05-10 12:00:00', '2026-05-12 14:30:00', 11700.00, 1, 1),
(2,'2026-05-12 15:45:10', '2026-05-14 10:00:00', 14500.00, 2, 2),
(3,'2026-05-13 09:20:00', '2026-05-15 11:30:00', 16200.00, 3, 3),
(4,'2026-05-14 17:10:00', '2026-05-16 13:00:00', 5400.00, 4, 4),
(5,'2026-05-15 10:30:00', '2026-05-18 09:00:00', 17300.00, 5, 5);



DROP TABLE IF EXISTS `StavkaPorudzbine`;

CREATE TABLE `StavkaPorudzbine` (
  `PorudzbinaID` BIGINT(10) UNSIGNED NOT NULL,
  `Rb` INT(7) NOT NULL,
  `Kolicina` INT(7) NOT NULL,
  `Cena` DECIMAL(10,2) NOT NULL,
  `Iznos` DECIMAL(10,2) NOT NULL,
  `AutoOpremaID` BIGINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`PorudzbinaID`,`Rb`),
  CONSTRAINT `fk_porudzbina_id` FOREIGN KEY (`PorudzbinaID`) REFERENCES `Porudzbina` (`PorudzbinaID`) ON DELETE CASCADE,
  CONSTRAINT `fk_auto_oprema_id` FOREIGN KEY (`AutoOpremaID`) REFERENCES `AutoOprema` (`AutoOpremaID`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;



INSERT INTO `StavkaPorudzbine` VALUES 
-- Stavke za porudzbinu 1
(1,1,1,3500.00,3500.00,1),
(1,2,1,8200.00,8200.00,2),

-- Stavke za porudzbinu 2
(2,1,1,14500.00,14500.00,7),

-- Stavke za porudzbinu 3
(3,1,1,6900.00,6900.00,5),
(3,2,1,7500.00,7500.00,6),
(3,3,1,1800.00,1800.00,4),

-- Stavke za porudzbinu 4
(4,1,1,2200.00,2200.00,8),
(4,2,1,3200.00,3200.00,10),

-- Stavke za porudzbinu 5
(5,1,1,9800.00,9800.00,12),
(5,2,1,4500.00,4500.00,3),
(5,3,1,3000.00,3000.00,11);



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;