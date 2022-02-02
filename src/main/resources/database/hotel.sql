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

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
                        `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
                        `role_name` varchar(255) DEFAULT NULL,
                        PRIMARY KEY (`role_id`),
                        UNIQUE KEY `UNIQUE` (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `role` */
insert into role(role_name) values('ADMIN');
insert into role(role_name) values('EMPLOYEE');
insert into role(role_name) values('USER');

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
                         `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
                         `username` varchar(255) NOT NULL,
                         `password` varchar(255) NOT NULL,
                         `first_name` varchar(255) NOT NULL,
                         `last_name` varchar(255) NOT NULL,
                         `date_of_birth` date NOT NULL,
                         `passport_number` varchar(255) NOT NULL,
                         `address` varchar(255) NOT NULL,
                         `email` varchar(255) NOT NULL,
                         `phone_number` varchar(255) NOT NULL,
                         `role_id` bigint(20) NOT NULL,
                         PRIMARY KEY (`user_id`),
                         UNIQUE KEY `UNIQUE` (`username`),
                         KEY `fk_user_role` (`role_id`),
                         CONSTRAINT `fk_user_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `users` */
insert into users(username,password,first_name,last_name,date_of_birth,passport_number,address,email,phone_number,role_id) values('boris','$2a$12$2wic8lX0g1.LgrrMQtbj9uIdRkhD2fGs81LgaxSZW9oW//GFE/UnG','Boris','Zivkov','1996-07-05','1234512413','Marka Tajcevica 6b','boris.zivkov96@gmail.com','+381649138692',(Select role_id from role where role_name = 'ADMIN'));
insert into users(username,password,first_name,last_name,date_of_birth,passport_number,address,email,phone_number,role_id) values('pera','$2a$12$mqLUoUF.niwPZq/UcISunu.mhcjMI2BAogDNKS2IwPaxgq3sSnMkm','Pera','Pera','1996-09-10','1234512414','Cara Dusana 7','pera.peric@gmail.com','+3816412345678',(Select role_id from role where role_name = 'EMPLOYEE'));
insert into users(username,password,first_name,last_name,date_of_birth,passport_number,address,email,phone_number,role_id) values('zika','$2a$12$93rmBxsTf73vh.v6k.B6LOg5y9hUVTyRSIwptBoCq0QV.fK4WNJ42','Zika','Zikic','1996-01-10','1234512415','Cara Dusana 20','zika.zikic@gmail.com','+3816412456789',(Select role_id from role where role_name = 'USER'));
/*Table structure for table `roomtype` */

DROP TABLE IF EXISTS `roomtype`;

CREATE TABLE `roomtype` (
                            `room_type_id` bigint(20) NOT NULL AUTO_INCREMENT,
                            `room_type_name` varchar(255) NOT NULL,
                            `price_per_day` double NOT NULL,
                            `description` varchar(255) NOT NULL,
                            PRIMARY KEY (`room_type_id`),
                            UNIQUE KEY `UNIQUE` (`room_type_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `roomtype` */

INSERT INTO roomtype(room_type_name, price_per_day, description) values ('Jednokrevetna',3499.99,'Jednostavna soba sa jednim krevetom');
INSERT INTO roomtype(room_type_name, price_per_day, description) values ('Dvokrevetna - Bracni krevet',5499.99,'Soba sa bracnim krevetom i malom terasom');
INSERT INTO roomtype(room_type_name, price_per_day, description) values ('Dvokrevetna - Odvojeni kreveti',5499.99,'Soba sa dva zasebna kreveta i malom terasom');
INSERT INTO roomtype(room_type_name, price_per_day, description) values ('Trokrevetna - Bracni krevet + 1',7499.99,'Soba sa bracnim krevetom i jos jednim dodatnim krevetom sa velikom terasom');
INSERT INTO roomtype(room_type_name, price_per_day, description) values ('Trokrevetna - Odvojeni kreveti',7499.99,'Soba sa tri zasebna kreveta sa velikom terasom');

/*Table structure for table `room` */

DROP TABLE IF EXISTS `room`;

CREATE TABLE `room` (
                        `room_id` bigint(20) NOT NULL AUTO_INCREMENT,
                        `room_number` int(11) NOT NULL,
                        `floor` int(11) NOT NULL,
                        `room_type_id` bigint(20) NOT NULL,
                        PRIMARY KEY (`room_id`),
                        KEY `fk_room_roomType` (`room_type_id`),
                        CONSTRAINT `fk_room_roomType` FOREIGN KEY (`room_type_id`) REFERENCES `roomtype` (`room_type_id`) ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `room` */

INSERT INTO room(room_number, floor, room_type_id) values (1,0,(Select room_type_id from roomtype where room_type_name='Jednokrevetna'));
INSERT INTO room(room_number, floor, room_type_id) values (2,0,(Select room_type_id from roomtype where room_type_name='Dvokrevetna - Bracni krevet'));
INSERT INTO room(room_number, floor, room_type_id) values (3,0,(Select room_type_id from roomtype where room_type_name='Dvokrevetna - Odvojeni kreveti'));
INSERT INTO room(room_number, floor, room_type_id) values (4,0,(Select room_type_id from roomtype where room_type_name='Trokrevetna - Bracni krevet + 1'));
INSERT INTO room(room_number, floor, room_type_id) values (5,0,(Select room_type_id from roomtype where room_type_name='Trokrevetna - Odvojeni kreveti'));

INSERT INTO room(room_number, floor, room_type_id) values (1,1,(Select room_type_id from roomtype where room_type_name='Jednokrevetna'));
INSERT INTO room(room_number, floor, room_type_id) values (2,1,(Select room_type_id from roomtype where room_type_name='Dvokrevetna - Bracni krevet'));
INSERT INTO room(room_number, floor, room_type_id) values (3,1,(Select room_type_id from roomtype where room_type_name='Dvokrevetna - Odvojeni kreveti'));
INSERT INTO room(room_number, floor, room_type_id) values (4,1,(Select room_type_id from roomtype where room_type_name='Trokrevetna - Bracni krevet + 1'));
INSERT INTO room(room_number, floor, room_type_id) values (5,1,(Select room_type_id from roomtype where room_type_name='Trokrevetna - Odvojeni kreveti'));

INSERT INTO room(room_number, floor, room_type_id) values (1,2,(Select room_type_id from roomtype where room_type_name='Jednokrevetna'));
INSERT INTO room(room_number, floor, room_type_id) values (2,2,(Select room_type_id from roomtype where room_type_name='Dvokrevetna - Bracni krevet'));
INSERT INTO room(room_number, floor, room_type_id) values (3,2,(Select room_type_id from roomtype where room_type_name='Dvokrevetna - Odvojeni kreveti'));
INSERT INTO room(room_number, floor, room_type_id) values (4,2,(Select room_type_id from roomtype where room_type_name='Trokrevetna - Bracni krevet + 1'));
INSERT INTO room(room_number, floor, room_type_id) values (5,2,(Select room_type_id from roomtype where room_type_name='Trokrevetna - Odvojeni kreveti'));

/*Table structure for table `service` */

DROP TABLE IF EXISTS `service`;

CREATE TABLE `service` (
                           `service_id` bigint(20) NOT NULL AUTO_INCREMENT,
                           `service_name` varchar(255) NOT NULL,
                           `price_per_use` double NOT NULL,
                           `description` varchar(255) DEFAULT NULL,
                           PRIMARY KEY (`service_id`),
                           UNIQUE KEY `UNIQUE` (`service_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `service` */

INSERT INTO service(service_name, price_per_use, description) values ('Masaza',1499.99,'Opustajuca masaza');
INSERT INTO service(service_name, price_per_use, description) values ('Sauna',4499.99,'Sauna za detoksikaciju');
INSERT INTO service(service_name, price_per_use, description) values ('Slana soba',1499.99,null);
INSERT INTO service(service_name, price_per_use, description) values ('Bazen',1499.99,'Olimpijski bazen duzine 100 metara');
INSERT INTO service(service_name, price_per_use, description) values ('Teniski teren',2499.99,'Termin od sat vremena');

/*Table structure for table `reservation` */

DROP TABLE IF EXISTS `reservation`;

CREATE TABLE `reservation` (
  `reservation_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `date_created` date NOT NULL,
  `total_sum` double NOT NULL,
  `note` varchar(255) DEFAULT NULL,
  `edited_at` date NULL,
  `user_id` bigint(20) NOT NULL,
  `employee_id` bigint(20) NOT NULL,
  `edited_by` bigint(20) NULL,
  PRIMARY KEY (`reservation_id`),
  KEY `fk_reservation_user` (`user_id`),
  CONSTRAINT `fk_reservation_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON UPDATE CASCADE ON DELETE RESTRICT,
  CONSTRAINT `fk_reservation_employee_user` FOREIGN KEY (`employee_id`) REFERENCES `users` (`user_id`) ON UPDATE CASCADE ON DELETE RESTRICT,
  CONSTRAINT `fk_reservation_edited_by_user` FOREIGN KEY (`edited_by`) REFERENCES `users` (`user_id`) ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `reservation` */

/*Table structure for table `reservationroom` */

DROP TABLE IF EXISTS `reservationroom`;

CREATE TABLE `reservationroom` (
    `reservation_id` bigint(20) NOT NULL,
    `room_id` bigint(20) NOT NULL,
    PRIMARY KEY (`reservation_id`,`room_id`),
    KEY `fk_reservationroom_reservation` (`reservation_id`),
    KEY `fk_reservationroom_room` (`room_id`),
    CONSTRAINT `fk_reservationroom_reservation` FOREIGN KEY (`reservation_id`) REFERENCES `reservation` (`reservation_id`) ON UPDATE CASCADE ON DELETE CASCADE ,
    CONSTRAINT `fk_reservationroom_room` FOREIGN KEY (`room_id`) REFERENCES `room` (`room_id`) ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `reservation` */

/*Table structure for table `reservationservice` */

DROP TABLE IF EXISTS `reservationservice`;

CREATE TABLE `reservationservice` (
  `reservation_id` bigint(20) NOT NULL,
  `service_id` bigint(20) NOT NULL,
  `number_of_usages` int(11) NOT NULL,
  PRIMARY KEY (`reservation_id`,`service_id`),
  KEY `fk_reservationservice_service` (`service_id`),
  CONSTRAINT `fk_reservationservice_reservation` FOREIGN KEY (`reservation_id`) REFERENCES `reservation` (`reservation_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_reservationservice_service` FOREIGN KEY (`service_id`) REFERENCES `service` (`service_id`) ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `reservationservice` */


/*Database destruction */
DROP TABLE iF EXISTS `reservationservice`;
DROP TABLE IF EXISTS `reservationroom`;
DROP TABLE IF EXISTS `reservation`;
DROP TABLE IF EXISTS `service`;
DROP TABLE IF EXISTS `room`;
DROP TABLE IF EXISTS `roomtype`;
DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `role`;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
