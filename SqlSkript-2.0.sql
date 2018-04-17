
-- drop database `nn_nst_2018`

CREATE DATABASE IF NOT EXISTS `nn_nst_2018` DEFAULT CHARACTER SET utf8;

USE `nn_nst_2018`;

DROP TABLE IF EXISTS `rola`;

CREATE TABLE `rola` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `naziv` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

INSERT INTO `rola` (id, naziv) VALUES (1,'ROLE_ADMIN');
INSERT INTO `rola` (id, naziv) VALUES (2,'ROLE_SEEKER');
-- INSERT INTO `rola` (id, naziv) VALUES (2,'ROLE_DESIGNER');

DROP TABLE IF EXISTS `secretQstn`;

CREATE TABLE `secretQstn` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `opis` VARCHAR(255) NOT NULL,
    `opis_US` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)  
) ENGINE=INNODB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

INSERT  INTO `secretQstn`(`id`,`opis`,`opis_US`) VALUES (1,'Koje je ime Vašeg omiljenog fudbalskog kluba?', 'What''s your favourite football team?');


DROP TABLE IF EXISTS `korisnik`; 

CREATE TABLE `korisnik` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `ime` VARCHAR(255) NOT NULL,
  `prezime` VARCHAR(255) NOT NULL,
  `username` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `lock` BIT DEFAULT 0,
  `qstnId` INT(11) NOT NULL,
  `qstnAns` VARCHAR(255) NOT NULL,
  `rolaId` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_korisnik_rola` FOREIGN KEY (`rolaId`) REFERENCES `rola` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_korisnik_SecretQstn` FOREIGN KEY (`qstnId`) REFERENCES `secretQstn` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=INNODB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

INSERT  INTO `korisnik`(`id`,`ime`,`prezime`,`username`,`password`,`qstnId`,`qstnAns`,`rolaId`) VALUES (1,'Nikola','Paunovic','paun','123',1,'Crvena zvezda',1);
INSERT  INTO `korisnik`(`id`,`ime`,`prezime`,`username`,`password`,`qstnId`,`qstnAns`,`rolaId`) VALUES (2,'Nikola','Solunac','soki','123',1,'Crvena zvezda',1);



DROP TABLE IF EXISTS `profil`;

CREATE TABLE `profil`(
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`korisnikId` INT(11) NOT NULL ,
	`datum` VARCHAR(10) NOT NULL,
	`naziv` VARCHAR(10) NULL,
	`opis` VARCHAR(255) NULL,
	PRIMARY KEY (`id`),
	CONSTRAINT `FK_profil_korisnik` FOREIGN KEY (`korisnikId`) REFERENCES `korisnik` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET=utf8;

CREATE TABLE `segment`(
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`profilId` INT(11) NOT NULL ,
	`heading` VARCHAR(255) NULL,
	`opis` VARCHAR(255) NULL,
	PRIMARY KEY (`id`),
	CONSTRAINT `FK_segment_profil` FOREIGN KEY (`profilId`) REFERENCES `profil` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET=utf8;

CREATE TABLE `stavka`(
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`segmentId` INT(11) NOT NULL,
	`datumOd` VARCHAR(10) NOT NULL,
	`datumDo` VARCHAR(10) NULL,
	`heading1` VARCHAR(255) NULL,
	`heading2` VARCHAR(255) NULL,
	`opis` VARCHAR(255) NULL,
	PRIMARY KEY (`id`),
	CONSTRAINT `FK_stavka_segment` FOREIGN KEY (`segmentId`) REFERENCES `segment` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET=utf8;





