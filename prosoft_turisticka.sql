/*
SQLyog Community v13.1.9 (64 bit)
MySQL - 10.4.22-MariaDB : Database - prosoft_turisticka
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`prosoft_turisticka` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `prosoft_turisticka`;

/*Table structure for table `agent` */

DROP TABLE IF EXISTS `agent`;

CREATE TABLE `agent` (
  `AgentID` int(20) NOT NULL AUTO_INCREMENT,
  `ImePrezime` varchar(50) NOT NULL,
  `KorisnickoIme` varchar(50) NOT NULL,
  `Sifra` varchar(50) NOT NULL,
  PRIMARY KEY (`AgentID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

/*Data for the table `agent` */

insert  into `agent`(`AgentID`,`ImePrezime`,`KorisnickoIme`,`Sifra`) values 
(1,'PetarPetric','admin','admin'),
(2,'PeraPeric','paki','paki'),
(3,'LazicLaza','laza','laza');

/*Table structure for table `aranzman` */

DROP TABLE IF EXISTS `aranzman`;

CREATE TABLE `aranzman` (
  `AranzmanID` int(20) NOT NULL AUTO_INCREMENT,
  `PoslovniPartneriD` int(20) NOT NULL,
  `Naziv` varchar(100) NOT NULL,
  `Destinacija` varchar(100) NOT NULL,
  `DatumPolaska` date NOT NULL,
  `DatumPovratka` date NOT NULL,
  `Cena` double NOT NULL,
  PRIMARY KEY (`AranzmanID`,`PoslovniPartneriD`),
  KEY `PoslovniPartneriD` (`PoslovniPartneriD`)
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=utf8mb4;

/*Data for the table `aranzman` */

insert  into `aranzman`(`AranzmanID`,`PoslovniPartneriD`,`Naziv`,`Destinacija`,`DatumPolaska`,`DatumPovratka`,`Cena`) values 
(11,1,'Letovanje','Rim','2024-10-12','2024-10-17',2000),
(12,1,'letovanje','Solun','2024-10-12','2024-10-17',10000);

/*Table structure for table `klijent` */

DROP TABLE IF EXISTS `klijent`;

CREATE TABLE `klijent` (
  `KlijentID` int(20) NOT NULL AUTO_INCREMENT,
  `Prezime` varchar(100) NOT NULL,
  `Ime` varchar(100) NOT NULL,
  `EmailAdresa` varchar(100) NOT NULL,
  `BrojTelefona` varchar(30) NOT NULL,
  `MestoID` int(20) NOT NULL,
  PRIMARY KEY (`KlijentID`),
  KEY `MestoID` (`MestoID`),
  CONSTRAINT `klijent_ibfk_1` FOREIGN KEY (`MestoID`) REFERENCES `mesto` (`MestoID`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4;

/*Data for the table `klijent` */

insert  into `klijent`(`KlijentID`,`Prezime`,`Ime`,`EmailAdresa`,`BrojTelefona`,`MestoID`) values 
(1,'Milan','Mikic','m@gmail.com','3325-986',1),
(3,'janjic','janko','321@e234','1232',2),
(4,'Peric','Petar','4321','23456',2),
(5,'pert','Petar','1234','1234',1),
(6,'jokic','Julija','123','3456',1);

/*Table structure for table `mesto` */

DROP TABLE IF EXISTS `mesto`;

CREATE TABLE `mesto` (
  `MestoID` int(20) NOT NULL AUTO_INCREMENT,
  `Ptt` varchar(50) NOT NULL,
  `Naziv` varchar(50) NOT NULL,
  PRIMARY KEY (`MestoID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `mesto` */

insert  into `mesto`(`MestoID`,`Ptt`,`Naziv`) values 
(1,'11000','Beograd'),
(2,'21000','Novi Sad');

/*Table structure for table `poslovnipartner` */

DROP TABLE IF EXISTS `poslovnipartner`;

CREATE TABLE `poslovnipartner` (
  `PoslovniPartnerID` int(20) NOT NULL,
  `PIB` int(30) NOT NULL,
  `MaticniBroj` int(30) NOT NULL,
  `Naziv` varchar(100) NOT NULL,
  `ZiroRacun` varchar(30) NOT NULL,
  `TipPoslovnogPartnera` varchar(30) NOT NULL,
  `Ulica` varchar(100) NOT NULL,
  `Broj` varchar(100) NOT NULL,
  `MestoID` int(20) NOT NULL,
  PRIMARY KEY (`PoslovniPartnerID`),
  KEY `MestoID` (`MestoID`),
  CONSTRAINT `poslovnipartner_ibfk_1` FOREIGN KEY (`MestoID`) REFERENCES `mesto` (`MestoID`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `poslovnipartner` */

insert  into `poslovnipartner`(`PoslovniPartnerID`,`PIB`,`MaticniBroj`,`Naziv`,`ZiroRacun`,`TipPoslovnogPartnera`,`Ulica`,`Broj`,`MestoID`) values 
(1,123456789,12870965,'Kontiki','123-980765',' smestaj ','Svetogorska','12',1),
(2,132453678,87654329,'Filip','123-09876','prevoz','Knez','1',2),
(3,232453675,18765432,'Travel','1234','prevoz','Vojislav Ilic','1',2);

/*Table structure for table `rezervacija` */

DROP TABLE IF EXISTS `rezervacija`;

CREATE TABLE `rezervacija` (
  `RezervacijaID` int(11) NOT NULL AUTO_INCREMENT,
  `AranzmanID` int(11) NOT NULL,
  `AgentID` int(11) NOT NULL,
  `KlijentID` int(11) NOT NULL,
  `Datum` date NOT NULL,
  `RokPlacanja` date NOT NULL,
  `Zaduzenje` double NOT NULL,
  `Avans` double NOT NULL,
  PRIMARY KEY (`RezervacijaID`,`AranzmanID`,`AgentID`,`KlijentID`),
  KEY `AgentID` (`AgentID`),
  KEY `AranzmanID` (`AranzmanID`),
  KEY `KlijentID` (`KlijentID`)
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8mb4;

/*Data for the table `rezervacija` */

insert  into `rezervacija`(`RezervacijaID`,`AranzmanID`,`AgentID`,`KlijentID`,`Datum`,`RokPlacanja`,`Zaduzenje`,`Avans`) values 
(11,11,1,1,'2024-07-27','2024-10-11',1200,800);

/*Table structure for table `stavkaranzmana` */

DROP TABLE IF EXISTS `stavkaranzmana`;

CREATE TABLE `stavkaranzmana` (
  `RbStavke` int(20) NOT NULL AUTO_INCREMENT,
  `AranzmanID` int(20) NOT NULL,
  `Naziv` varchar(100) NOT NULL,
  `Opis` varchar(200) NOT NULL,
  PRIMARY KEY (`RbStavke`,`AranzmanID`),
  KEY `AranzmanID` (`AranzmanID`)
) ENGINE=InnoDB AUTO_INCREMENT=1112 DEFAULT CHARSET=utf8mb4;

/*Data for the table `stavkaranzmana` */

insert  into `stavkaranzmana`(`RbStavke`,`AranzmanID`,`Naziv`,`Opis`) values 
(111,11,'avion','komfor');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
