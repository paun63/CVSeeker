/*
SQLyog Ultimate v10.00 Beta1
MySQL - 5.5.20 : Database - nn_nst_2018
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`nn_nst_2018` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `nn_nst_2018`;

/*Table structure for table `korisnik` */

DROP TABLE IF EXISTS `korisnik`;

CREATE TABLE `korisnik` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ime` varchar(255) NOT NULL,
  `prezime` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `lock` bit(1) DEFAULT b'0',
  `qstnId` int(11) NOT NULL,
  `qstnAns` varchar(255) NOT NULL,
  `rolaId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_korisnik_rola` (`rolaId`),
  KEY `FK_korisnik_SecretQstn` (`qstnId`),
  CONSTRAINT `FK_korisnik_rola` FOREIGN KEY (`rolaId`) REFERENCES `rola` (`id`),
  CONSTRAINT `FK_korisnik_SecretQstn` FOREIGN KEY (`qstnId`) REFERENCES `secretqstn` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `korisnik` */

insert  into `korisnik`(`id`,`ime`,`prezime`,`username`,`password`,`lock`,`qstnId`,`qstnAns`,`rolaId`) values (1,'Nikola','Paunovic','paun','123','\0',1,'Crvena zvezda',1),(2,'Nikola','Solunac','soki','123','\0',1,'Crvena zvezda',1),(3,'Nemanja','Boskovic','bosko','12345678922','',1,'asd',2),(4,'Dusko','Leontijevic','duki','123456789','\0',1,'qwe',2);

/*Table structure for table `profil` */

DROP TABLE IF EXISTS `profil`;

CREATE TABLE `profil` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `korisnikId` int(11) NOT NULL,
  `datum` varchar(10) NOT NULL,
  `naziv` varchar(10) DEFAULT NULL,
  `opis` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_profil_korisnik` (`korisnikId`),
  CONSTRAINT `FK_profil_korisnik` FOREIGN KEY (`korisnikId`) REFERENCES `korisnik` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `profil` */

insert  into `profil`(`id`,`korisnikId`,`datum`,`naziv`,`opis`) values (1,1,'2018.01.01','asd','asd');

/*Table structure for table `rola` */

DROP TABLE IF EXISTS `rola`;

CREATE TABLE `rola` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `rola` */

insert  into `rola`(`id`,`naziv`) values (1,'ROLE_ADMIN'),(2,'ROLE_SEEKER');

/*Table structure for table `secretqstn` */

DROP TABLE IF EXISTS `secretqstn`;

CREATE TABLE `secretqstn` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `opis` varchar(255) NOT NULL,
  `opis_US` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `secretqstn` */

insert  into `secretqstn`(`id`,`opis`,`opis_US`) values (1,'Koje je ime Vaseg omiljenog fudbalskog kluba?','What\'s your favourite football team?'),(2,'U kom gradu ste rodjeni?','In which city were you born?'),(3,'Koja je Vasa omiljena boja?','Which is your favourite color ?');

/*Table structure for table `segment` */

DROP TABLE IF EXISTS `segment`;

CREATE TABLE `segment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `profilId` int(11) NOT NULL,
  `heading` varchar(255) DEFAULT NULL,
  `opis` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_segment_profil` (`profilId`),
  CONSTRAINT `FK_segment_profil` FOREIGN KEY (`profilId`) REFERENCES `profil` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `segment` */

insert  into `segment`(`id`,`profilId`,`heading`,`opis`) values (1,1,'1','asd');

/*Table structure for table `sifarnik` */

DROP TABLE IF EXISTS `sifarnik`;

CREATE TABLE `sifarnik` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `sifarnik` */

insert  into `sifarnik`(`id`,`naziv`) values (1,'O sebi'),(2,'Radno iskustvo'),(3,'Edukacija'),(4,'Dodatne aktivnosti'),(5,'Jezici'),(6,'Interesovanja'),(7,'Test'),(13,'asd');

/*Table structure for table `stavka` */

DROP TABLE IF EXISTS `stavka`;

CREATE TABLE `stavka` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `segmentId` int(11) NOT NULL,
  `datumOd` varchar(10) NOT NULL,
  `datumDo` varchar(10) DEFAULT NULL,
  `heading1` varchar(255) DEFAULT NULL,
  `heading2` varchar(255) DEFAULT NULL,
  `opis` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_stavka_segment` (`segmentId`),
  CONSTRAINT `FK_stavka_segment` FOREIGN KEY (`segmentId`) REFERENCES `segment` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `stavka` */

insert  into `stavka`(`id`,`segmentId`,`datumOd`,`datumDo`,`heading1`,`heading2`,`opis`) values (1,1,'asd','asd','asd','asd','asd');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
