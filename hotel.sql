-- --------------------------------------------------------
-- Host:                         localhost
-- Versión del servidor:         8.0.22 - MySQL Community Server - GPL
-- SO del servidor:              Win64
-- HeidiSQL Versión:             11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Volcando estructura de base de datos para poo
CREATE DATABASE IF NOT EXISTS `poo` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `poo`;

-- Volcando estructura para tabla poo.booking
CREATE TABLE IF NOT EXISTS `booking` (
  `idBooking` int NOT NULL AUTO_INCREMENT,
  `checkIn` date NOT NULL,
  `checkOut` date NOT NULL,
  `createdAt` date NOT NULL,
  `breakfastIncluded` tinyint NOT NULL,
  `parking` tinyint NOT NULL,
  `cost` float NOT NULL,
  `guest` int NOT NULL,
  `room` int NOT NULL,
  `version` int DEFAULT NULL,
  PRIMARY KEY (`idBooking`),
  KEY `guest_idx` (`guest`),
  KEY `room_idx` (`room`),
  CONSTRAINT `guest` FOREIGN KEY (`guest`) REFERENCES `user` (`idUser`),
  CONSTRAINT `room` FOREIGN KEY (`room`) REFERENCES `room` (`idRoom`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla poo.booking: ~0 rows (aproximadamente)
DELETE FROM `booking`;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;

-- Volcando estructura para tabla poo.cancellation
CREATE TABLE IF NOT EXISTS `cancellation` (
  `idCancellation` int NOT NULL AUTO_INCREMENT,
  `createdAt` date NOT NULL,
  `booking` int NOT NULL,
  `version` int DEFAULT NULL,
  PRIMARY KEY (`idCancellation`),
  KEY `booking_idx` (`booking`),
  CONSTRAINT `booking2` FOREIGN KEY (`booking`) REFERENCES `booking` (`idBooking`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla poo.cancellation: ~0 rows (aproximadamente)
DELETE FROM `cancellation`;
/*!40000 ALTER TABLE `cancellation` DISABLE KEYS */;
/*!40000 ALTER TABLE `cancellation` ENABLE KEYS */;

-- Volcando estructura para tabla poo.payment
CREATE TABLE IF NOT EXISTS `payment` (
  `idpayment` int NOT NULL AUTO_INCREMENT,
  `createdAt` date NOT NULL,
  `booking` int NOT NULL,
  `card` varchar(45) NOT NULL,
  `cardNumber` varchar(45) NOT NULL,
  `version` int DEFAULT NULL,
  PRIMARY KEY (`idpayment`),
  KEY `booking_idx` (`booking`),
  CONSTRAINT `booking` FOREIGN KEY (`booking`) REFERENCES `booking` (`idBooking`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla poo.payment: ~0 rows (aproximadamente)
DELETE FROM `payment`;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;

-- Volcando estructura para tabla poo.room
CREATE TABLE IF NOT EXISTS `room` (
  `idRoom` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `price` float NOT NULL,
  `occupancy` int NOT NULL,
  `availability` int NOT NULL,
  `facilities` varchar(45) NOT NULL,
  `version` int DEFAULT NULL,
  PRIMARY KEY (`idRoom`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla poo.room: ~6 rows (aproximadamente)
DELETE FROM `room`;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` (`idRoom`, `name`, `price`, `occupancy`, `availability`, `facilities`, `version`) VALUES
	(1, 'SIMPLE', 1000, 1, 3, 'TV', NULL),
	(2, 'SIMPLE', 2000, 1, 3, 'TV COCINA', NULL),
	(3, 'DOBLE', 4000, 2, 3, 'TV', NULL),
	(4, 'DOBLE', 5000, 2, 3, 'TV COCINA AIRE', NULL),
	(5, 'TRIPLE', 6000, 3, 3, 'TV', NULL),
	(6, 'TRIPLE', 7000, 3, 3, 'TV COCINA AIRE', NULL);
/*!40000 ALTER TABLE `room` ENABLE KEYS */;

-- Volcando estructura para tabla poo.user
CREATE TABLE IF NOT EXISTS `user` (
  `idUser` int NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `password` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `birthDate` date NOT NULL,
  `nationality` varchar(45) NOT NULL,
  `version` int DEFAULT NULL,
  PRIMARY KEY (`idUser`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla poo.user: ~1 rows (aproximadamente)
DELETE FROM `user`;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`idUser`, `email`, `password`, `firstName`, `lastName`, `birthDate`, `nationality`, `version`) VALUES
	(18, 'marcelomrios83@gmail.com', '$2a$10$JIX.Ph8ByLCKxx.g8tmTOugN8PYSqepd6kLFwboEQXl3N9qCHYRfu', 'Marcelo', 'Rios', '2020-11-01', 'ARG', 0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
