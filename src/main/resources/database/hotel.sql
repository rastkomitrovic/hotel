/*
SQLyog Community v13.1.5  (64 bit)
MySQL - 10.4.11-MariaDB : Database - hotel
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`hotel` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `hotel`;

/*Table structure for table `reservation` */

DROP TABLE IF EXISTS `reservation`;

CREATE TABLE `reservation` (
  `reservationId` bigint(20) NOT NULL AUTO_INCREMENT,
  `startDate` date NOT NULL,
  `endDate` date NOT NULL,
  `totalSum` double NOT NULL,
  `note` varchar(255) DEFAULT NULL,
  `userId` bigint(20) NOT NULL,
  `roomId` bigint(20) NOT NULL,
  PRIMARY KEY (`reservationId`),
  KEY `fk_reservation_user` (`userId`),
  KEY `fk_reservation_room` (`roomId`),
  CONSTRAINT `fk_reservation_room` FOREIGN KEY (`roomId`) REFERENCES `room` (`roomId`) ON UPDATE CASCADE,
  CONSTRAINT `fk_reservation_user` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `reservation` */

/*Table structure for table `reservationservice` */

DROP TABLE IF EXISTS `reservationservice`;

CREATE TABLE `reservationservice` (
  `reservationId` bigint(20) NOT NULL,
  `serviceId` bigint(20) NOT NULL,
  `numberOfUsages` int(11) NOT NULL,
  PRIMARY KEY (`reservationId`,`serviceId`),
  KEY `fk_reservationservice_service` (`serviceId`),
  CONSTRAINT `fk_reservationservice_reservation` FOREIGN KEY (`reservationId`) REFERENCES `reservation` (`reservationId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_reservationservice_service` FOREIGN KEY (`serviceId`) REFERENCES `service` (`serviceId`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `reservationservice` */

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `roleId` bigint(20) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`roleId`),
  UNIQUE KEY `UNIQUE` (`roleName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `role` */

/*Table structure for table `room` */

DROP TABLE IF EXISTS `room`;

CREATE TABLE `room` (
  `roomId` bigint(20) NOT NULL AUTO_INCREMENT,
  `roomNumber` int(11) NOT NULL,
  `floor` int(11) NOT NULL,
  `roomTypeId` bigint(20) NOT NULL,
  PRIMARY KEY (`roomId`),
  UNIQUE KEY `UNIQUE` (`roomNumber`),
  KEY `fk_room_roomType` (`roomTypeId`),
  CONSTRAINT `fk_room_roomType` FOREIGN KEY (`roomTypeId`) REFERENCES `roomtype` (`roomTypeId`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `room` */

/*Table structure for table `roomtype` */

DROP TABLE IF EXISTS `roomtype`;

CREATE TABLE `roomtype` (
  `roomTypeId` bigint(20) NOT NULL AUTO_INCREMENT,
  `roomTypeName` varchar(255) NOT NULL,
  `pricePerDay` double NOT NULL,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`roomTypeId`),
  UNIQUE KEY `UNIQUE` (`roomTypeName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `roomtype` */

/*Table structure for table `service` */

DROP TABLE IF EXISTS `service`;

CREATE TABLE `service` (
  `serviceId` bigint(20) NOT NULL AUTO_INCREMENT,
  `serviceName` varchar(255) NOT NULL,
  `pricePerUse` double NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`serviceId`),
  UNIQUE KEY `UNIQUE` (`serviceName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `service` */

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `userId` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `firstName` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `dateOfBirth` date NOT NULL,
  `address` varchar(255) NOT NULL,
  `roleId` bigint(20) NOT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `UNIQUE` (`username`),
  KEY `fk_user_role` (`roleId`),
  CONSTRAINT `fk_user_role` FOREIGN KEY (`roleId`) REFERENCES `role` (`roleId`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `users` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
